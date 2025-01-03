package com.ben.infrastructure.persistent.repository;

import com.ben.domain.web.model.entity.CategoryEntity;
import com.ben.domain.web.model.entity.CategoryArticlePageEntity;
import com.ben.domain.web.repository.ICategoryRepository;
import com.ben.infrastructure.persistent.dao.IArticleCategoryRelDao;
import com.ben.infrastructure.persistent.dao.IArticleDao;
import com.ben.infrastructure.persistent.dao.ICategoryDao;
import com.ben.infrastructure.persistent.po.Article;
import com.ben.infrastructure.persistent.po.ArticleCategoryRel;
import com.ben.infrastructure.persistent.po.Category;
import com.ben.types.enums.ResponseCode;
import com.ben.types.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-12  10:33
 * @Description: 文章分类仓储服务
 * @Version: 1.0
 */
@Slf4j
@Component
public class CategoryRepository implements ICategoryRepository {

    @Autowired
    private ICategoryDao categoryDao;

    @Autowired
    private IArticleCategoryRelDao articleCategoryRelDao;

    @Autowired
    private IArticleDao articleDao;

    @Override
    public List<CategoryEntity> findCategorySelectList(Long size) {
        List<Category> categoryList = null;
        // 如果接口入参中未指定 size
        if (Objects.isNull(size) || size == 0) {
            // 查询所有分类
            categoryList = categoryDao.findCategoryList();
        } else {
            // 否则查询指定的数量
            categoryList = categoryDao.selectByLimit(size);
        }
        if (categoryList == null) return null;
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryEntity categoryEntity = CategoryEntity.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .createTime(category.getCreateTime())
                    .updateTime(category.getUpdateTime())
                    .articlesTotal(category.getArticlesTotal())
                    .build();
            categoryEntities.add(categoryEntity);
        }
        return categoryEntities;
    }

    @Override
    public PageInfo<CategoryArticlePageEntity> findCategoryArticlePageList(Long categoryId, Integer current, Integer size) {
        // 0. 判断category是否存在
        Category categoryDO = categoryDao.selectByCategoryId(categoryId);
        // 判断该分类是否存在
        if (Objects.isNull(categoryDO)) {
            log.warn("==> 该分类不存在, categoryId: {}", categoryId);
            throw new BizException(ResponseCode.CATEGORY_NOT_EXISTED);
        }

        // 1. 分页查询categoryId下的所有文章id；
        PageHelper.startPage(current, size);
        List<ArticleCategoryRel> articleCategoryRels = articleCategoryRelDao.selectListByCategoryId(categoryId);
        // 若该分类下未发布任何文章
        if (CollectionUtils.isEmpty(articleCategoryRels)) {
            log.info("==> 该分类下还未发布任何文章, categoryId: {}", categoryId);
            return new PageInfo<>(null);
        }
        PageInfo<ArticleCategoryRel> pageInfo = new PageInfo<>(articleCategoryRels);
        List<ArticleCategoryRel> articleCategoryInfoList = pageInfo.getList();// 分页查询的数据；
        List<Long> articleIds = articleCategoryInfoList.stream().map(ArticleCategoryRel::getArticleId).collect(Collectors.toList());
        // 2. 查询文章表；
        List<Article> articles = articleDao.selectPageListByArticleIds(articleIds);
        // 3. 填充响应
        List<CategoryArticlePageEntity> resList = articles.stream().map(article ->
                        CategoryArticlePageEntity.builder()
                                .id(article.getId())
                                .title(article.getTitle())
                                .cover(article.getCover())
                                .createDate(article.getCreateTime().toLocalDate())
                                .build()
                )
                .collect(Collectors.toList());

        return new PageInfo<>(resList);
    }
}
