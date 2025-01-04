package com.ben.domain.admin.service.impl;

import com.ben.domain.admin.repository.IAdminStatisticsRepository;
import com.ben.domain.admin.service.IAdminStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-04  17:48
 * @Description: 文章数据统计
 * @Version: 1.0
 */
@Service
@Slf4j
public class AdminStatisticsServiceImpl implements IAdminStatisticsService {

    @Autowired
    private IAdminStatisticsRepository repository;
    @Override
    public void statisticsCategoryArticleTotal() {
        repository.statisticsCategoryArticleTotal();
    }

    @Override
    public void statisticsTagArticleTotal() {
        repository.statisticsTagArticleTotal();
    }
}