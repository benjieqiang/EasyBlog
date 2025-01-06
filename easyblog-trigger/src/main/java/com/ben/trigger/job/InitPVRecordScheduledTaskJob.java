package com.ben.trigger.job;

import com.ben.infrastructure.persistent.dao.IStatisticsArticlePVDao;
import com.ben.infrastructure.persistent.dao.po.StatisticsArticlePV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author benjieqiang
 * @description 初始化 PV 访问量定时任务
 * @date 2025/1/4 11:00 AM
 */
@Component
@Slf4j
public class InitPVRecordScheduledTaskJob {

    @Autowired
    private IStatisticsArticlePVDao statisticsArticlePVDao;

    @Scheduled(cron = "0 0 23 * * ?") // 每天晚间 23 点执行
    public void execute() {
        // 定时任务执行的业务逻辑
        log.info("==> 开始执行初始化明日 PV 访问量记录定时任务");

        // 当日日期
        LocalDate currDate = LocalDate.now();

        // 明日
        LocalDate tomorrowDate = currDate.plusDays(1);

        // 组装插入的记录
        StatisticsArticlePV articlePV = StatisticsArticlePV.builder()
                .pvDate(tomorrowDate) // 明日的文章 pv 访问量
                .pvCount(0L) // 默认阅读量为 0
                .build();

        statisticsArticlePVDao.insert(articlePV);
        log.info("==> 结束执行初始化明日 PV 访问量记录定时任务");
    }
}
