package com.ben.infrastructure.persistent.repository;

import com.ben.domain.admin.model.entity.ArticleEntity;
import com.ben.domain.admin.repository.IAdminArticleRepository;
import com.ben.infrastructure.persistent.dao.*;
import com.ben.infrastructure.persistent.po.*;
import com.ben.types.enums.ResponseCode;
import com.ben.types.exception.BizException;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    }

    /**
     * 保存标签
     *
     * @param articleId
     * @param publishTags
     */
    private void insertTags(Long articleId, List<String> publishTags) {
        // 筛选提交的标签（表中不存在的标签）
        List<String> notExistTags = null;
        // 筛选提交的标签（表中已存在的标签）
        List<String> existedTags = null;

        // 1. 查询出所有标签
        List<Tag> tagList = tagDao.findTagList();

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

        // 3. 插入表
        // 3.1 tag表中已存在标签，只需往articleTagRelDao插入
        if (!CollectionUtils.isEmpty(existedTags)) {
            List<ArticleTagRel> articleTagRelList = Lists.newArrayList();
            existedTags.forEach(tagId -> {
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
        if (!CollectionUtils.isEmpty(notExistTags)) {
            // 需要先将标签入库，拿到对应标签 ID 后，再把文章-标签关联关系入库
            List<ArticleTagRel> articleTagRelList = Lists.newArrayList();
            notExistTags.forEach(tagName -> {
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
    }
}