package com.ben.infrastructure.persistent.repository;

import com.ben.domain.web.model.entity.CategoryEntity;
import com.ben.domain.web.repository.ICategoryRepository;
import com.ben.infrastructure.persistent.dao.ICategoryDao;
import com.ben.infrastructure.persistent.po.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<CategoryEntity> findCategorySelectList() {
        List<Category> categoryList = categoryDao.findCategoryList();
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
}
