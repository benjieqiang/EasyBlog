package com.ben.domain.admin.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardPVStatisticsInfoEntity {
    /**
     * 日期集合
     */
    private List<String> pvDates;

    /**
     * PV 浏览量集合
     */
    private List<Long> pvCounts;
}
