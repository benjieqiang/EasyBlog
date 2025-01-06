package com.ben.domain.web.adapter.repository;

import com.ben.domain.web.model.entity.CategoryEntity;
import com.ben.domain.web.model.entity.CategoryArticlePageEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-12  10:26
 * @Description: 文章分类仓储服务
 * @Version: 1.0
 */
public interface ICategoryRepository {

    List<CategoryEntity> findCategorySelectList(Long size);

    PageInfo<CategoryArticlePageEntity> findCategoryArticlePageList(Long id, Integer current, Integer size);
}
