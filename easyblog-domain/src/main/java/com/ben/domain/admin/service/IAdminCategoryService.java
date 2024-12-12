package com.ben.domain.admin.service;

import com.ben.domain.admin.model.entity.CategoryEntity;
import com.ben.domain.admin.model.entity.CategoryPageEntity;
import com.ben.types.response.PageResponse;
import com.ben.types.response.Response;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @InterfaceName: IAdminCategoryService
 * @Description: 文章分类管理
 * @Author: benjieqiang
 * @LastChangeDate: 2024/12/12 10:25 AM
 * @Version: v1.0
 */

public interface IAdminCategoryService {
    void addCategory(String name);

    PageInfo<CategoryEntity> findCategoryPageList(CategoryPageEntity categoryPageEntity);

    int deleteCategory(Long id);

    List<CategoryEntity> findCategorySelectList();
}

