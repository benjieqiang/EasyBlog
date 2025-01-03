package com.ben.domain.web.service.impl;

import com.ben.domain.web.model.entity.CategoryEntity;
import com.ben.domain.web.model.entity.CategoryArticlePageEntity;
import com.ben.domain.web.repository.ICategoryRepository;
import com.ben.domain.web.service.ICategoryService;
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
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> findCategorySelectList(Long size) {

        return categoryRepository.findCategorySelectList(size);
    }

    @Override
    public PageInfo<CategoryArticlePageEntity> findCategoryArticlePageList(Long categoryId, Integer current, Integer size) {
        return categoryRepository.findCategoryArticlePageList(categoryId, current, size);
    }
}
