package com.ben.domain.web.service;


import com.ben.domain.web.model.entity.BlogSettingEntity;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  14:58
 * @Description: 博客设置service
 * @Version: 1.0
 */
public interface IBlogSettingService {

    BlogSettingEntity findDetail(Long id);
}
