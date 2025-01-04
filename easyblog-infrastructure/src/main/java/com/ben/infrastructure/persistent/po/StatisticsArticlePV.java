package com.ben.infrastructure.persistent.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author benjieqiang
 * @description 每日文章 PV 访问量统计
 * @date 2025/1/3 10:44 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticsArticlePV {
    /* id */
    private Long id;
    /* 被统计的日期 */
    private LocalDate pvDate;
    /* pv访问量 */
    private Long pvCount;
    /* 创建时间 */
    private LocalDateTime createTime;
    /* 最后一次更新时间 */
    private LocalDateTime updateTime;
}
