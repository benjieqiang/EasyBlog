package com.ben.domain.admin.repository;

import com.ben.domain.admin.model.entity.DashboardPVStatisticsInfoEntity;
import com.ben.domain.admin.model.entity.DashboardStatisticsInfoEntity;

import java.time.LocalDate;
import java.util.Map;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-03  22:11
 * @Description: 仪表盘仓储
 * @Version: 1.0
 */
public interface IAdminDashBoardRepository {
    DashboardStatisticsInfoEntity findDashboardStatistics();

    DashboardPVStatisticsInfoEntity findDashboardPVStatistics();

    Map<LocalDate, Long> findDashboardPublishArticleStatistics();
}
