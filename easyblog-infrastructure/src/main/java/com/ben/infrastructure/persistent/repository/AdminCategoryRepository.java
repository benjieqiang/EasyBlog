package com.ben.infrastructure.persistent.repository;

import com.ben.domain.admin.model.entity.CategoryEntity;
import com.ben.domain.admin.model.entity.CategoryPageEntity;
import com.ben.domain.admin.repository.IAdminCategoryRepository;
import com.ben.infrastructure.persistent.dao.ICategoryDao;
import com.ben.infrastructure.persistent.po.Category;
import com.ben.types.enums.ResponseCode;
import com.ben.types.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-12  10:33
 * @Description: 文章分类仓储服务
 * @Version: 1.0
 */
@Slf4j
@Component
public class AdminCategoryRepository implements IAdminCategoryRepository {

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    public void insert(String name) {
        Category category = categoryDao.selectByName(name);
        if (category != null) {
            log.warn("分类名称： {}, 此已存在", name);
            throw new BizException(ResponseCode.CATEGORY_NAME_IS_EXISTED);
        }
        categoryDao.insert(name);
    }

    @Override
    public PageInfo<CategoryEntity> findCategoryPageList(CategoryPageEntity categoryPageEntity) {
        Integer pageNum = categoryPageEntity.getPageNum();
        Integer pageSize = categoryPageEntity.getPageSize();
        String name = categoryPageEntity.getName();
        LocalDateTime startDate = categoryPageEntity.getStartDate();
        LocalDateTime endDate = categoryPageEntity.getEndDate();
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);

        List<Category> categories = categoryDao.selectPageList(name, startDate, endDate);
        if (categories == null) return null;
        List<CategoryEntity> categoryEntities = new ArrayList<>(categories.size());
        for (Category category : categories) {
            CategoryEntity categoryEntity = CategoryEntity.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .createTime(category.getCreateTime())
                    .updateTime(category.getUpdateTime())
                    .articlesTotal(category.getArticlesTotal())
                    .build();
            categoryEntities.add(categoryEntity);
        }
        return new PageInfo<>(categoryEntities);
    }

    @Override
    public int deleteCategory(Long id) {
        // todo: 如果category下面有文章，不能删除，直接抛出异常；
        return categoryDao.updateCategory(id);
    }

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