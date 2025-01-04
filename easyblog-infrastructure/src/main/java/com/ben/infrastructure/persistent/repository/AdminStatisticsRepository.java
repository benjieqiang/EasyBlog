package com.ben.infrastructure.persistent.repository;

import com.ben.domain.admin.repository.IAdminStatisticsRepository;
import com.ben.infrastructure.persistent.dao.IArticleCategoryRelDao;
import com.ben.infrastructure.persistent.dao.IArticleTagRelDao;
import com.ben.infrastructure.persistent.dao.ICategoryDao;
import com.ben.infrastructure.persistent.dao.ITagDao;
import com.ben.infrastructure.persistent.po.ArticleCategoryRel;
import com.ben.infrastructure.persistent.po.ArticleTagRel;
import com.ben.infrastructure.persistent.po.Category;
import com.ben.infrastructure.persistent.po.Tag;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-04  17:50
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Component
public class AdminStatisticsRepository implements IAdminStatisticsRepository {
    @Autowired
    private ICategoryDao categoryDao;
    @Autowired
    private IArticleCategoryRelDao articleCategoryRelDao;
    @Autowired
    private ITagDao tagDao;
    @Autowired
    private IArticleTagRelDao articleTagRelDao;

    @Override
    public void statisticsCategoryArticleTotal() {
        // 查询所有分类
        List<Category> categories = categoryDao.selectALl();

        // 查询所有文章-分类映射记录
        List<ArticleCategoryRel> categoryRels = articleCategoryRelDao.selectAll();

        // 按所属分类 ID 进行分组
        Map<Long, List<ArticleCategoryRel>> categoryIdAndArticleCategoryRelMap = Maps.newHashMap();
        // 如果不为空
        if (!CollectionUtils.isEmpty(categoryRels)) {
            categoryIdAndArticleCategoryRelMap = categoryRels.stream().collect(Collectors.groupingBy(ArticleCategoryRel::getCategoryId));
        }

        if (!CollectionUtils.isEmpty(categories)) {
            // 循环统计各分类下的文章总数
            for (Category category : categories) {
                Long categoryId = category.getId();
                // 获取此分类下所有映射记录
                List<ArticleCategoryRel> articleCategoryRelDOList = categoryIdAndArticleCategoryRelMap.get(categoryId);

                // 获取文章总数
                int articlesTotal = CollectionUtils.isEmpty(articleCategoryRelDOList) ? 0 : articleCategoryRelDOList.size();

                // 更新该分类的文章总数
                categoryDao.update(Category.builder()
                        .id(categoryId)
                        .name(category.getName())
                        .articlesTotal(articlesTotal)
                        .build());
            }
        }
    }

    /**
     * 统计各标签下文章总数
     */
    @Override
    public void statisticsTagArticleTotal() {
        // 查询所有标签
        List<Tag> tags = tagDao.selectAll();

        // 查询所有文章-标签映射记录
        List<ArticleTagRel> articleTagRels = articleTagRelDao.selectAll();

        // 按所属标签 ID 进行分组
        Map<Long, List<ArticleTagRel>> tagIdAndArticleTagRelMap = Maps.newHashMap();
        // 如果不为空
        if (!CollectionUtils.isEmpty(articleTagRels)) {
            tagIdAndArticleTagRelMap = articleTagRels.stream().collect(Collectors.groupingBy(ArticleTagRel::getTagId));
        }

        if (!CollectionUtils.isEmpty(tags)) {
            // 循环统计各标签下的文章总数
            for (Tag tag : tags) {
                Long tagId = tag.getId();

                // 获取此标签下所有映射记录
                List<ArticleTagRel> articleTagRelDOList = tagIdAndArticleTagRelMap.get(tagId);

                // 获取文章总数
                int articlesTotal = CollectionUtils.isEmpty(articleTagRelDOList) ? 0 : articleTagRelDOList.size();

                // 更新该标签的文章总数
                tagDao.update(Tag.builder()
                        .id(tagId)
                        .name(tag.getName())
                        .articlesTotal(articlesTotal)
                        .build());
            }
        }
    }
}
