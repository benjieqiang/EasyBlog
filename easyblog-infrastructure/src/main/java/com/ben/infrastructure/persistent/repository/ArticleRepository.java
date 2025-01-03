package com.ben.infrastructure.persistent.repository;

import com.ben.domain.web.model.aggregate.IndexArticleDetailAggregate;
import com.ben.domain.web.model.entity.ArticleEntity;
import com.ben.domain.web.model.entity.IndexArticlePageEntity;
import com.ben.domain.web.model.entity.TagEntity;
import com.ben.domain.web.repository.IArticleRepository;
import com.ben.infrastructure.persistent.dao.*;
import com.ben.infrastructure.persistent.po.*;
import com.ben.types.enums.ResponseCode;
import com.ben.types.exception.BizException;
import com.ben.types.utils.MarkdownStatsUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-02  18:34
 * @Description: 首页文章仓储
 * @Version: 1.0
 */
@Slf4j
@Component
public class ArticleRepository implements IArticleRepository {
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

    @Override
    public PageInfo<IndexArticlePageEntity> findArticlePageList(Integer current, Integer size) {
        // 开启分页
        PageHelper.startPage(current, size);
        // 1. 分页查询文章表
        List<Article> articles = articleDao.selectPageList(null, null, null, null);
        if (Objects.isNull(articles)) {
            log.warn("==> 查询的文章不存在");
            throw new BizException(ResponseCode.ARTICLE_NOT_FOUND);
        }
        PageInfo<Article> articlePageInfo = new PageInfo<>(articles);
        List<Article> articlePageInfoList = articlePageInfo.getList(); // 分页查询的数据；
        List<Long> articleIds = articlePageInfoList.stream().map(Article::getId).collect(Collectors.toList());

        // 2. 构造文章基础信息
        List<IndexArticlePageEntity> articleEntities = articles.stream().map(article ->
                IndexArticlePageEntity.builder()
                        .id(article.getId())
                        .title(article.getTitle())
                        .cover(article.getCover())
                        .createTime(article.getCreateTime())
                        .build()
        ).collect(Collectors.toList());

        // 3. 查询所属分类
        List<ArticleCategoryRel> articleCategoryRels = articleCategoryRelDao.selectByArticleIds(articleIds);
        List<Long> categoryIds = articleCategoryRels.stream().map(ArticleCategoryRel::getCategoryId).collect(Collectors.toList());
        List<Category> categories = categoryDao.selectByCategoryIds(categoryIds);
        // categoryid:name
        Map<Long, String> categoryIdNameMap = categories.stream().collect(Collectors.toMap(Category::getId, Category::getName));
        articleEntities.forEach(
                entity -> {
                    // 找匹配的文章id集合，返回值是一个 Optional<ArticleCategoryRel>，表示可能找到一个结果，也可能没有结果
                    Long currArticleId = entity.getId();
                    Optional<ArticleCategoryRel> optional = articleCategoryRels.stream().filter(rel -> Objects.equals(rel.getArticleId(), currArticleId)).findAny();
                    // 若不为空
                    if (optional.isPresent()) {
                        ArticleCategoryRel articleCategoryRel = optional.get();
                        Long categoryId = articleCategoryRel.getCategoryId();
                        // 通过分类 ID 从 map 中拿到对应的分类名称
                        String categoryName = categoryIdNameMap.get(categoryId);
                        entity.setCategoryId(categoryId);
                        entity.setCategoryName(categoryName);
                    }
                }
        );

        // 4. 查询标签列表集合；
        List<ArticleTagRel> articleTagRels = articleTagRelDao.selectByArticleIds(articleIds);
        List<Long> ids = articleTagRels.stream()
                .map(ArticleTagRel::getTagId)
                .collect(Collectors.toList());

        List<Tag> tags = tagDao.selectByIds(ids);

        // tagId: tagName
        Map<Long, String> tagIdNameMap = tags.stream().collect(Collectors.toMap(Tag::getId, Tag::getName));

        // 填充tag集合到对应文章id下面
        articleEntities.forEach(entity -> {
            // 拿到真正的tagid：articleId集合
            Long currArticleId = entity.getId();
            List<ArticleTagRel> articleTagRelList = articleTagRels.stream().filter(rel -> Objects.equals(rel.getArticleId(), currArticleId)).collect(Collectors.toList());
            List<TagEntity> tagEntityList = new ArrayList<>();
            articleTagRelList.forEach(
                    articleTagRel -> {
                        Long tagId = articleTagRel.getTagId();
                        tagEntityList.add(TagEntity.builder()
                                .id(tagId)
                                .name(tagIdNameMap.get(tagId))
                                .build());
                    }
            );
            entity.setTags(tagEntityList);
        });

        return new PageInfo<>(articleEntities);
    }

    @Override
    public IndexArticleDetailAggregate findArticleDetail(Long articleId) {
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
        Category category = categoryDao.selectByCategoryId(articleCategoryRel.getCategoryId());

        // 4. 查询标签列表集合；
        List<ArticleTagRel> articleTagRels = articleTagRelDao.selectByArticleId(articleId);
        List<Long> ids = articleTagRels.stream()
                .map(ArticleTagRel::getTagId)
                .collect(Collectors.toList());

        List<Tag> tags = tagDao.selectByIds(ids);

        List<TagEntity> tagEntities = tags.stream().map(
                tag -> TagEntity.builder()
                        .id(tag.getId())
                        .name(tag.getName())
                        .build()
        ).collect(Collectors.toList());

        // 5. 查询上一篇文章 + 查询下一篇文章
        Article preArticle = articleDao.selectPreArticle(articleId);
        ArticleEntity preArticleEntity = null;
        if (Objects.nonNull(preArticle)) {
            preArticleEntity = ArticleEntity.builder().articleId(preArticle.getId()).title(preArticle.getTitle()).build();
        }
        ArticleEntity nextArticleEntity = null;
        Article nextArticle = articleDao.selectNextArticle(articleId);
        if (Objects.nonNull(preArticle)) {
            nextArticleEntity = ArticleEntity.builder().articleId(nextArticle.getId()).title(nextArticle.getTitle()).build();
        }

        // 6. 总字数 + readingTime
        int calculateWordCount = MarkdownStatsUtil.calculateWordCount(articleContent.getContent());
        String readingTime = MarkdownStatsUtil.calculateReadingTime(calculateWordCount);

        return IndexArticleDetailAggregate.builder()
                .title(article.getTitle())
                .content(articleContent.getContent())
                .createTime(article.getCreateTime())
                .updateTime(article.getUpdateTime())
                .categoryId(articleCategoryRel.getCategoryId())
                .categoryName(category.getName())
                .readNum(article.getReadNum())
                .tags(tagEntities)
                .preArticle(preArticleEntity)
                .nextArticle(nextArticleEntity)
                .totalWords(calculateWordCount)
                .readTime(readingTime)
                .build();
    }
}
