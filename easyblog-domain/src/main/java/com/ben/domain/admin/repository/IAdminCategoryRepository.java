package com.ben.domain.admin.repository;

import com.ben.domain.admin.model.entity.CategoryEntity;
import com.ben.domain.admin.model.entity.CategoryPageEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-12  10:26
 * @Description: 文章分类仓储服务
 * @Version: 1.0
 */
public interface IAdminCategoryRepository {
    void insert(String name);

    PageInfo<CategoryEntity> findCategoryPageList(CategoryPageEntity categoryPageEntity);

    int deleteCategory(Long id);

    List<CategoryEntity> findCategorySelectList();
}
