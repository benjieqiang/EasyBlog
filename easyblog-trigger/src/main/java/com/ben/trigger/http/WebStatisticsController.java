package com.ben.trigger.http;

import com.ben.domain.web.model.entity.StatisticsInfoEntity;
import com.ben.domain.web.service.IStatisticsService;
import com.ben.trigger.http.dto.statistics.FindIndexStatisticsInfoRspDTO;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-04  12:06
 * @Description: 前台-数据展示模块
 * @Version: 1.0
 */
@RestController
@Slf4j
@Api(tags = "前台 数据展示模块")
@RequestMapping("/api/${app.config.api-version}/statistics")
public class WebStatisticsController {
    @Autowired
    private IStatisticsService statisticsService;

    @PostMapping("/info")
    @ApiOperation(value = "获取基础统计信息")
    @ApiOperationLog(description = "获取基础统计信息")
    public Response findStatistics() {
        StatisticsInfoEntity statisticsInfoEntity = statisticsService.findStatistics();

        return Response.success(FindIndexStatisticsInfoRspDTO.builder()
                .articleTotalCount(statisticsInfoEntity.getArticleTotalCount())
                .pvTotalCount(statisticsInfoEntity.getPvTotalCount())
                .tagTotalCount(statisticsInfoEntity.getTagTotalCount())
                .categoryTotalCount(statisticsInfoEntity.getCategoryTotalCount())
                .build());
    }
}
