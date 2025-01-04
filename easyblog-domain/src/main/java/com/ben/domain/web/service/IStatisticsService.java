package com.ben.domain.web.service;

import com.ben.domain.web.model.entity.StatisticsInfoEntity;

/**
 * @InterfaceName: IStatisticsRepository
 * @Description: 前端 数据展示服务
 * @Author: benjieqiang
 * @LastChangeDate: 2025/1/4 12:02 PM
 * @Version: v1.0
 */

public interface IStatisticsService {

    StatisticsInfoEntity findStatistics();
}

