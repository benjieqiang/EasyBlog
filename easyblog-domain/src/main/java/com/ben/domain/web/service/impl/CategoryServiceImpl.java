package com.ben.domain.web.service.impl;

import com.ben.domain.web.model.entity.CategoryEntity;
import com.ben.domain.web.repository.ICategoryRepository;
import com.ben.domain.web.service.ICategoryService;
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
    public List<CategoryEntity> findCategorySelectList() {

        return categoryRepository.findCategorySelectList();
    }
}
