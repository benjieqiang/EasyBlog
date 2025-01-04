package com.ben.trigger.http;

import com.ben.domain.admin.model.entity.DashboardPVStatisticsInfoEntity;
import com.ben.domain.admin.model.entity.DashboardStatisticsInfoEntity;
import com.ben.domain.admin.service.IAdminDashboardService;
import com.ben.trigger.http.dto.dashboard.FindDashboardPVStatisticsInfoRspDTO;
import com.ben.trigger.http.dto.dashboard.FindDashboardStatisticsInfoRspDTO;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-03  22:01
 * @Description: Admin-仪表盘
 * @Version: 1.0
 */
@RestController
@Slf4j
@Api(tags = "Admin-仪表盘")
@RequestMapping("/api/${app.config.api-version}/admin/dashboard")
public class AdminDashboardController {
    @Autowired
    private IAdminDashboardService dashboardService;

    @PostMapping("/statistics")
    @ApiOperation(value = "获取后台仪表盘基础统计信息")
    @ApiOperationLog(description = "获取后台仪表盘基础统计信息")
    public Response findDashboardStatistics() {
        DashboardStatisticsInfoEntity statisticsInfoEntity = dashboardService.findDashboardStatistics();

        return Response.success(FindDashboardStatisticsInfoRspDTO.builder()
                .articleTotalCount(statisticsInfoEntity.getArticleTotalCount())
                .pvTotalCount(statisticsInfoEntity.getPvTotalCount())
                .tagTotalCount(statisticsInfoEntity.getTagTotalCount())
                .categoryTotalCount(statisticsInfoEntity.getCategoryTotalCount())
                .build());
    }

    @PostMapping("/publishArticle/statistics")
    @ApiOperation(value = "获取后台仪表盘文章发布热点统计信息")
    @ApiOperationLog(description = "获取后台仪表盘文章发布热点统计信息")
    public Response findDashboardPublishArticleStatistics() {
        Map<LocalDate, Long> dateCountMap = dashboardService.findDashboardPublishArticleStatistics();
        return Response.success(dateCountMap);
    }

    @PostMapping("/pv/statistics")
    @ApiOperation(value = "获取后台仪表盘最近一周 PV 访问量信息")
    @ApiOperationLog(description = "获取后台仪表盘最近一周 PV 访问量信息")
    public Response findDashboardPVStatistics() {
        DashboardPVStatisticsInfoEntity pvStatisticsInfoEntity = dashboardService.findDashboardPVStatistics();
        return Response.success(FindDashboardPVStatisticsInfoRspDTO.builder()
                .pvCounts(pvStatisticsInfoEntity.getPvCounts())
                .pvDates(pvStatisticsInfoEntity.getPvDates())
                .build());

    }
}
