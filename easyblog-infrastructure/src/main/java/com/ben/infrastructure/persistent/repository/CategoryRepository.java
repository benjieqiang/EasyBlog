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
import java.util.Objects;

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
}
