package com.ben.domain.admin.service;

import com.ben.domain.admin.model.entity.BlogSettingEntity;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  14:58
 * @Description: 博客设置service
 * @Version: 1.0
 */
public interface IAdminBlogSettingService {

    void updateBlogSetting(BlogSettingEntity blogSettingEntity);

    BlogSettingEntity findDetail(Long id);
}
