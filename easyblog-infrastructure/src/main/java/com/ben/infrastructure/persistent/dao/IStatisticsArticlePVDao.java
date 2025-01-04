package com.ben.infrastructure.persistent.dao;

import com.ben.infrastructure.persistent.po.StatisticsArticlePV;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-03  22:47
 * @Description: 每日文章 PV 访问量统计DAO
 * @Version: 1.0
 */
@Mapper
public interface IStatisticsArticlePVDao {
    /* 插入 */
    void insert(StatisticsArticlePV statisticsArticlePV);

    /* 对指定日期的文章 PV 访问量进行 +1 */
    int increasePVCount(LocalDate pvDate);

    /* 查询最近一周的文章 PV 访问量记录 */
    List<StatisticsArticlePV> selectLatestWeekRecords();
}
