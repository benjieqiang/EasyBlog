package com.ben.domain.admin.service.impl;

import com.ben.domain.admin.model.entity.CategoryEntity;
import com.ben.domain.admin.model.entity.CategoryPageEntity;
import com.ben.domain.admin.repository.IAdminCategoryRepository;
import com.ben.domain.admin.service.IAdminCategoryService;
import com.ben.types.response.PageResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-12  15:13
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class AdminCategoryServiceImpl implements IAdminCategoryService {
    @Autowired
    private IAdminCategoryRepository categoryRepository;

    @Override
    public void addCategory(String name) {
        categoryRepository.insert(name);
    }

    @Override
    public PageInfo<CategoryEntity> findCategoryPageList(CategoryPageEntity categoryPageEntity) {
        return categoryRepository.findCategoryPageList(categoryPageEntity);
    }

    @Override
    public int deleteCategory(Long id) {
        return categoryRepository.deleteCategory(id);
    }

    @Override
    public List<CategoryEntity> findCategorySelectList() {
        return categoryRepository.findCategorySelectList();
    }
}
