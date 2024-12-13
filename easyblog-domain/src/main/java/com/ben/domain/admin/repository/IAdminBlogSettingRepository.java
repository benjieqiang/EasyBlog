package com.ben.domain.admin.repository;

import com.ben.domain.admin.model.entity.BlogSettingEntity;
import com.ben.domain.admin.model.entity.CategoryEntity;
import com.ben.domain.admin.model.entity.CategoryPageEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-12  10:26
 * @Description: 博客设置
 * @Version: 1.0
 */
public interface IAdminBlogSettingRepository {
    void insert(BlogSettingEntity blogSettingEntity);

    int update(BlogSettingEntity blogSettingEntity);

    BlogSettingEntity findBlogSetting(Long id);
}
