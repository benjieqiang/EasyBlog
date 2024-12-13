package com.ben.domain.admin.service.impl;

import com.ben.domain.admin.model.entity.BlogSettingEntity;
import com.ben.domain.admin.repository.IAdminBlogSettingRepository;
import com.ben.domain.admin.service.IAdminBlogSettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  14:59
 * @Description: 博客设置service impl
 * @Version: 1.0
 */
@Slf4j
@Service
public class AdminBlogSettingServiceImpl implements IAdminBlogSettingService {
    @Autowired
    private IAdminBlogSettingRepository repository;

    @Override
    public void updateBlogSetting(BlogSettingEntity blogSettingEntity) {
        // Long id = blogSettingEntity.getId();
        // 表中只有一条数据
        BlogSettingEntity blogSetting = repository.findBlogSetting(1L);
        if (blogSetting != null) {
            repository.update(blogSettingEntity);
        } else {
            repository.insert(blogSettingEntity);
        }
    }

    @Override
    public BlogSettingEntity findDetail(Long id) {
        return repository.findBlogSetting(id);
    }
}
