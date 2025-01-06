package com.ben.domain.web.adapter.repository;

import com.ben.domain.web.model.entity.BlogSettingEntity;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-12  10:26
 * @Description: 博客设置
 * @Version: 1.0
 */
public interface IBlogSettingRepository {
    BlogSettingEntity findBlogSetting(Long id);
}
