package com.ben.domain.admin.service;

import com.ben.domain.admin.model.entity.DashboardPVStatisticsInfoEntity;
import com.ben.domain.admin.model.entity.DashboardStatisticsInfoEntity;

import java.time.LocalDate;
import java.util.Map;

/**
 * @InterfaceName: IAdminDashboardService
 * @Description: Admin 仪表盘
 * @Author: benjieqiang
 * @LastChangeDate: 2025/1/3 10:02 PM
 * @Version: v1.0
 */

public interface IAdminDashboardService {
    DashboardStatisticsInfoEntity findDashboardStatistics();

    DashboardPVStatisticsInfoEntity findDashboardPVStatistics();

    Map<LocalDate, Long> findDashboardPublishArticleStatistics();
}

