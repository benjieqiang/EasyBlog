package com.ben.domain.web.service;

import com.ben.domain.web.model.entity.CategoryEntity;
import com.ben.domain.web.model.entity.CategoryArticlePageEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description: 文章分类管理
 * @Author: benjieqiang
 * @LastChangeDate: 2024/12/12 10:25 AM
 * @Version: v1.0
 */

public interface ICategoryService {

    List<CategoryEntity> findCategorySelectList(Long size);

    PageInfo<CategoryArticlePageEntity> findCategoryArticlePageList(Long categoryId, Integer current, Integer size);
}

