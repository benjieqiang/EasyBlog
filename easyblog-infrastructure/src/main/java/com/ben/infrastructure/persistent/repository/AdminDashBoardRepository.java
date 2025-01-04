package com.ben.infrastructure.persistent.repository;

import com.ben.domain.admin.model.entity.DashboardPVStatisticsInfoEntity;
import com.ben.domain.admin.model.entity.DashboardStatisticsInfoEntity;
import com.ben.domain.admin.repository.IAdminDashBoardRepository;
import com.ben.infrastructure.persistent.dao.IArticleDao;
import com.ben.infrastructure.persistent.dao.ICategoryDao;
import com.ben.infrastructure.persistent.dao.IStatisticsArticlePVDao;
import com.ben.infrastructure.persistent.dao.ITagDao;
import com.ben.infrastructure.persistent.po.ArticleCount;
import com.ben.infrastructure.persistent.po.StatisticsArticlePV;
import com.ben.types.common.DateTimeConstants;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-03  22:12
 * @Description: 仪表盘仓储
 * @Version: 1.0
 */
@Slf4j
@Repository
public class AdminDashBoardRepository implements IAdminDashBoardRepository {
    @Autowired
    private IArticleDao articleDao;

    @Autowired
    private ITagDao tagDao;

    @Autowired
    private ICategoryDao categoryDao;

    @Autowired
    private IStatisticsArticlePVDao statisticsArticlePVDao;

    @Override
    public DashboardStatisticsInfoEntity findDashboardStatistics() {
        // 1. 查询文章总数
        Long articleTotalCount = articleDao.selectCount();
        // 2. 查询分类总数
        Long categoryTotalCount = categoryDao.selectCount();

        // 3. 查询标签总数
        Long tagTotalCount = tagDao.selectCount();

        // 4. 总浏览量
        Long readNum = articleDao.selectAllReadNum();

        return DashboardStatisticsInfoEntity.builder()
                .articleTotalCount(articleTotalCount)
                .tagTotalCount(tagTotalCount)
                .categoryTotalCount(categoryTotalCount)
                .pvTotalCount(readNum)
                .build();
    }

    @Override
    public DashboardPVStatisticsInfoEntity findDashboardPVStatistics() {
        // 查询最近一周的 PV 访问量记录
        List<StatisticsArticlePV> statisticsArticlePVS = statisticsArticlePVDao.selectLatestWeekRecords();

        // 日期集合
        List<String> pvDates = Lists.newArrayList();
        // PV 集合
        List<Long> pvCounts = Lists.newArrayList();
        DashboardPVStatisticsInfoEntity vo = null;
        if (!CollectionUtils.isEmpty(statisticsArticlePVS)) {
            // 转 Map, 方便后续通过日期获取 PV 访问量
            Map<LocalDate, Long> pvDateCountMap = statisticsArticlePVS.stream()
                    .collect(Collectors.toMap(StatisticsArticlePV::getPvDate, StatisticsArticlePV::getPvCount));

            // 当前日期
            LocalDate currDate = LocalDate.now();
            // 一周前
            LocalDate tmpDate = currDate.minusWeeks(1);
            // 从一周前开始循环
            for (; tmpDate.isBefore(currDate) || tmpDate.isEqual(currDate); tmpDate = tmpDate.plusDays(1)) {
                // 设置对应日期的 PV 访问量
                pvDates.add(tmpDate.format(DateTimeConstants.MONTH_DAY_FORMATTER));
                Long pvCount = pvDateCountMap.get(tmpDate);
                pvCounts.add(Objects.isNull(pvCount) ? 0 : pvCount);
            }
        }
        return DashboardPVStatisticsInfoEntity.builder()
                .pvDates(pvDates)
                .pvCounts(pvCounts)
                .build();
    }

    @Override
    public Map<LocalDate, Long> findDashboardPublishArticleStatistics() {
        // 当前日期
        LocalDate currDate = LocalDate.now();

        // 起始日期：上一年
        LocalDate startDate = currDate.minusYears(1);

        // 查找这一年内，每日发布的文章数量
        List<ArticleCount> articleCounts = articleDao.selectDateArticlePublishCount(startDate, currDate);

        // 有序 Map, 返回的日期文章数需要以升序排列
        Map<LocalDate, Long> map = Maps.newLinkedHashMap();
        if (!CollectionUtils.isEmpty(articleCounts)) {
            // map {date:count}
            Map<LocalDate, Long> dateArticleCountMap = articleCounts.stream()
                    .collect(Collectors.toMap(ArticleCount::getDate, ArticleCount::getCount));

            // 从上一年的今天循环到今天
            for (; startDate.isBefore(currDate) || startDate.isEqual(currDate); startDate = startDate.plusDays(1)) {
                // 以日期作为 key 从 dateArticleCountMap 中取文章发布总量
                Long count = dateArticleCountMap.get(startDate);
                // 设置到返参 Map
                map.put(startDate, Objects.isNull(count) ? 0 : count);
            }
        }
        return map;
    }
}
