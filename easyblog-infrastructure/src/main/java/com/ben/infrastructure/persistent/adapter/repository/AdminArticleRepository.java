package com.ben.infrastructure.persistent.adapter.repository;

import com.ben.domain.admin.event.DeleteArticleEvent;
import com.ben.domain.admin.event.PublishArticleEvent;
import com.ben.domain.admin.event.UpdateArticleEvent;
import com.ben.domain.admin.model.aggregate.ArticleDetailAggregate;
import com.ben.domain.admin.model.entity.ArticleEntity;
import com.ben.domain.admin.model.entity.ArticlePageEntity;
import com.ben.domain.admin.repository.IAdminArticleRepository;
import com.ben.infrastructure.persistent.dao.*;
import com.ben.infrastructure.persistent.dao.po.*;
import com.ben.types.enums.ResponseCode;
import com.ben.types.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-30  15:59
 * @Description: 文章仓储实现
 * @Version: 1.0
 */
@Repository
@Slf4j
public class AdminArticleRepository implements IAdminArticleRepository {
    @Autowired
    private IArticleDao articleDao;

    @Autowired
    private IArticleContentDao articleContentDao;

    @Autowired
    private IArticleCategoryRelDao articleCategoryRelDao;

    @Autowired
    private IArticleTagRelDao articleTagRelDao;

    @Autowired
    private ITagDao tagDao;

    @Autowired
    private ICategoryDao categoryDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void publishArticle(ArticleEntity articleEntity) {
        Long categoryId = articleEntity.getCategoryId();
        List<String> tags = articleEntity.getTags();

        // 0. 分类id是否存在
        Category category = categoryDao.selectByCategoryId(categoryId);
        if (Objects.isNull(category)) {
            log.warn("==> 分类不存在, categoryId: {}", categoryId);
            throw new BizException(ResponseCode.CATEGORY_NOT_EXISTED);
        }

        Article article = Article.builder()
                .title(articleEntity.getTitle())
                .cover(articleEntity.getCover())
                .summary(articleEntity.getSummary())
                .type(articleEntity.getType())
                .build();

        transactionTemplate.execute(status -> {
            try {
                // 1. 插入文章表
                articleDao.insert(article);
                // 1.1 查询articleId
                Long articleId = article.getId();
                ArticleContent articleContent = ArticleContent.builder()
                        .articleId(articleId)
                        .content(articleEntity.getContent())
                        .build();
                // 2. 插入文章正文表
                articleContentDao.insert(articleContent);
                // 3. 插入文章分类表
                ArticleCategoryRel articleCategoryRel = ArticleCategoryRel.builder()
                        .articleId(articleId)
                        .categoryId(categoryId)
                        .build();
                articleCategoryRelDao.insert(articleCategoryRel);
                // 4. 批量插入文章tag
                insertTags(articleId, tags);
                return 1;
            } catch (Exception e) {
                status.setRollbackOnly();
                log.error("写入文章记录，失败", e);
                throw new BizException(ResponseCode.INSERT_FAILED);
            }
        });
        // 文章发布，通过lucene更新索引
        eventPublisher.publishEvent(new PublishArticleEvent(this, article.getId()));
    }

    /**
     * 保存标签
     *
     * @param articleId
     * @param publishTags
     */
    private void insertTags(Long articleId, List<String> publishTags) {
        // 筛选提交的标签（表中不存在的标签）
        List<String> notExistTags = Lists.newArrayList();
        // 筛选提交的标签（表中已存在的标签）
        List<String> existedTags = Lists.newArrayList();

        // 1. 查询出所有标签
        List<Tag> tagList = tagDao.selectAll();

        // 2. 判断tag表是否为空
        // 2.1 空表：则existedTags=null,notExistTags !=null;
        if (CollectionUtils.isEmpty(tagList)) {
            notExistTags = publishTags;
        } else {
            // 2.2. 从tagIds从筛选：已存在（小写转大写）和不存在，分别记录；
            List<String> tagIds = tagList.stream().map(tag -> String.valueOf(tag.getId())).collect(Collectors.toList());
            // 1. 标签存在于标签表
            existedTags = publishTags.stream().filter(publishTag -> tagIds.contains(publishTag)).collect(Collectors.toList());
            // 2. 不存在标签表
            notExistTags = publishTags.stream().filter(publishTag -> !tagIds.contains(publishTag)).collect(Collectors.toList());

            // 3. 处理大小写不一致的标签，Map，存储小写标签名与对应 ID 的映射
            Map<String, Long> tagNameIdMap = tagList.stream()
                    .collect(Collectors.toMap(tag -> tag.getName().toLowerCase(), Tag::getId));

            // 4. 使用迭代器安全的删除操作
            Iterator<String> iterator = notExistTags.iterator();
            while (iterator.hasNext()) {
                String notExistTag = iterator.next();
                // 转小写, 若 Map 中相同的 key，则表示该新标签是重复标签，表中已存在，不需要再插入
                if (tagNameIdMap.containsKey(notExistTag.toLowerCase())) {
                    // 删除不存在列表中的标签
                    iterator.remove();
                    // 加入已存在的标签集合
                    existedTags.add(String.valueOf(tagNameIdMap.get(notExistTag.toLowerCase())));
                }
            }
        }

        List<String> finalExistedTags = existedTags;
        List<String> finalNotExistTags = notExistTags;

        transactionTemplate.execute(status -> {
            try {
                // 3. 插入表
                // 3.1 tag表中已存在标签，只需往articleTagRelDao插入
                if (!CollectionUtils.isEmpty(finalExistedTags)) {
                    List<ArticleTagRel> articleTagRelList = Lists.newArrayList();
                    finalExistedTags.forEach(tagId -> {
                        ArticleTagRel articleTagRel = ArticleTagRel.builder()
                                .articleId(articleId)
                                .tagId(Long.valueOf(tagId))
                                .build();
                        articleTagRelList.add(articleTagRel);
                    });
                    // 批量插入
                    articleTagRelDao.insertBatch(articleTagRelList);
                }

                // 3.2 tag表中不存在：先插入tag表，再插入tag-文章关联表
                if (!CollectionUtils.isEmpty(finalNotExistTags)) {
                    // 需要先将标签入库，拿到对应标签 ID 后，再把文章-标签关联关系入库
                    List<ArticleTagRel> articleTagRelList = Lists.newArrayList();
                    finalNotExistTags.forEach(tagName -> {
                        Tag tag = Tag.builder()
                                .name(tagName)
                                .build();
                        tagDao.insert(tag);
                        // 拿到保存的标签 ID
                        Long tagId = tag.getId();
                        // 文章-标签关联关系
                        ArticleTagRel articleTagRel = ArticleTagRel.builder()
                                .articleId(articleId)
                                .tagId(tagId)
                                .build();
                        articleTagRelList.add(articleTagRel);
                    });
                    // 批量插入
                    articleTagRelDao.insertBatch(articleTagRelList);
                }
                return 1;
            } catch (Exception e) {
                status.setRollbackOnly();
                log.error("插入文章记录，失败", e);
                throw new BizException(ResponseCode.INSERT_FAILED);
            }
        });
    }

    @Override
    public void deleteArticleById(Long id) {
        transactionTemplate.execute(status -> {
            try {
                // 1. 删除文章
                articleDao.deleteByArticleId(id);
                // 2. 删除文章内容
                articleContentDao.deleteByArticleId(id);
                // 3. 删除文章-分类关联记录
                articleCategoryRelDao.deleteByArticleId(id);
                // 4. 删除文章-标签关联记录
                articleTagRelDao.deleteByArticleId(id);
                return 1;
            } catch (Exception e) {
                status.setRollbackOnly();
                log.error("删除文章记录，失败", e);
                throw new BizException(ResponseCode.ARTICLE_DELETE_FAILED);
            }
        });

        // 发布文章删除事件
        eventPublisher.publishEvent(new DeleteArticleEvent(this, id));
    }

    @Override
    public PageInfo<ArticleEntity> findArticlePageList(ArticlePageEntity articlePageEntity) {
        Integer pageNum = articlePageEntity.getPageNum();
        Integer pageSize = articlePageEntity.getPageSize();
        String title = articlePageEntity.getTitle();
        Integer type = articlePageEntity.getType();
        LocalDateTime startDate = articlePageEntity.getStartDate();
        LocalDateTime endDate = articlePageEntity.getEndDate();
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);

        List<Article> articles = articleDao.selectPageList(title, startDate, endDate, type);
        if (articles == null) return null;
        List<ArticleEntity> articleEntities = new ArrayList<>(articles.size());
        for (Article article : articles) {
            ArticleEntity articleEntity = ArticleEntity.builder()
                    .articleId(article.getId())
                    .title(article.getTitle())
                    .cover(article.getCover())
                    .createTime(article.getCreateTime())
                    .isTop(article.getWeight() > 0)
                    .build();
            articleEntities.add(articleEntity);
        }
        return new PageInfo<>(articleEntities);

    }

    @Override
    public ArticleDetailAggregate findArticleDetail(Long articleId) {
        // 1. 查询文章表详情；
        Article article = articleDao.selectByArticleId(articleId);
        if (Objects.isNull(article)) {
            log.warn("==> 查询的文章不存在，articleId: {}", articleId);
            throw new BizException(ResponseCode.ARTICLE_NOT_FOUND);
        }
        // 2. 查询正文表详情
        ArticleContent articleContent = articleContentDao.selectByArticleId(articleId);
        // 3. 查询分类表；
        ArticleCategoryRel articleCategoryRel = articleCategoryRelDao.selectByArticleId(articleId);
        // 4. 查询标签列表集合；
        List<ArticleTagRel> articleTagRels = articleTagRelDao.selectByArticleId(articleId);
        // 获取对应标签 ID 集合
        List<Long> tagIds = articleTagRels.stream().map(ArticleTagRel::getTagId).collect(Collectors.toList());

        return ArticleDetailAggregate.builder()
                .articleId(articleId)
                .title(article.getTitle())
                .cover(article.getCover())
                .summary(article.getSummary())
                .content(articleContent.getContent())
                .categoryId(articleCategoryRel.getCategoryId())
                .tagIds(tagIds)
                .build();
    }

    @Override
    public void updateArticle(ArticleEntity articleEntity) {
        Long articleId = articleEntity.getArticleId();
        Long categoryId = articleEntity.getCategoryId();
        List<String> tags = articleEntity.getTags();
        ArticleContent articleContent = ArticleContent.builder()
                .content(articleEntity.getContent())
                .articleId(articleId)
                .build();

        Article article = Article.builder()
                .id(articleId)
                .title(articleEntity.getTitle())
                .cover(articleEntity.getCover())
                .summary(articleEntity.getSummary())
                .build();

        transactionTemplate.execute(status -> {
            try {
                // 1. 更新文章表；失败则抛异常；
                int count = articleDao.update(article);
                if (count == 0) {
                    log.warn("==> 该文章不存在, articleId: {}", articleId);
                    throw new BizException(ResponseCode.ARTICLE_NOT_FOUND);
                }
                // 2. 更新content表；
                articleContentDao.update(articleContent);

                // 3. 更新文章分类表: 先查分类是否存在，存在则删除老分类，不存在直接抛异常
                ArticleCategoryRel articleCategoryRel = articleCategoryRelDao.selectByArticleId(articleId);
                if (Objects.isNull(articleCategoryRel)) {
                    log.warn("==> 分类不存在, categoryId: {}", categoryId);
                    throw new BizException(ResponseCode.CATEGORY_NOT_EXISTED);
                }
                articleCategoryRelDao.deleteByArticleId(articleId);
                ArticleCategoryRel categoryRel = ArticleCategoryRel.builder()
                        .articleId(articleId)
                        .categoryId(categoryId)
                        .build();
                articleCategoryRelDao.insert(categoryRel);

                // 4. 更新文章标签表: 先删除老标签关联，再插入新标签(插入时不存在的标签重新插入)
                articleTagRelDao.deleteByArticleId(articleId);
                insertTags(articleId, tags);
                return 1;
            } catch (Exception e) {
                status.setRollbackOnly();
                log.error("更新文章记录，失败", e);
                throw new BizException(ResponseCode.ARTICLE_UPDATED_FAILED);
            }
        });
        // 发布文章修改事件
        eventPublisher.publishEvent(new UpdateArticleEvent(this, articleId));
    }

    @Override
    public void updateArticleIsTop(Long articleId, Boolean isTop) {
        Integer maxWeight = 0;
        if (isTop) {
            // 查询最大权重值；
            Article maxWeightArticle = articleDao.selectMaxWeight();
            maxWeight = maxWeightArticle.getWeight() + 1;
        }
        // 更新该文章id,置顶：最大权重+1，不置顶：weight置0；
        Article article = Article.builder().id(articleId).weight(maxWeight).build();
        articleDao.update(article);
    }
}
