package com.ben.domain.admin.service.impl;

import com.ben.domain.admin.model.entity.DashboardPVStatisticsInfoEntity;
import com.ben.domain.admin.model.entity.DashboardStatisticsInfoEntity;
import com.ben.domain.admin.repository.IAdminDashBoardRepository;
import com.ben.domain.admin.service.IAdminDashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-03  22:02
 * @Description: 仪表盘
 * @Version: 1.0
 */
@Slf4j
@Service
public class AdminDashboardServiceImpl implements IAdminDashboardService {

    @Autowired
    private IAdminDashBoardRepository dashBoardRepository;

    @Override
    public DashboardStatisticsInfoEntity findDashboardStatistics() {
        return dashBoardRepository.findDashboardStatistics();
    }

    @Override
    public DashboardPVStatisticsInfoEntity findDashboardPVStatistics() {
        return dashBoardRepository.findDashboardPVStatistics();
    }

    @Override
    public Map<LocalDate, Long> findDashboardPublishArticleStatistics() {
        return dashBoardRepository.findDashboardPublishArticleStatistics();
    }
}
