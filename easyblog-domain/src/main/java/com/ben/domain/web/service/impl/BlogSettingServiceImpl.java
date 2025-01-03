package com.ben.domain.web.service.impl;

import com.ben.domain.web.model.entity.BlogSettingEntity;
import com.ben.domain.web.repository.IBlogSettingRepository;
import com.ben.domain.web.service.IBlogSettingService;
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
public class BlogSettingServiceImpl implements IBlogSettingService {
    @Autowired
    private IBlogSettingRepository repository;

    @Override
    public BlogSettingEntity findDetail(Long id) {
        return repository.findBlogSetting(id);
    }
}
