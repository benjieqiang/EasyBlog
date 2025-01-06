package com.ben.domain.web.service.impl;

import com.ben.domain.web.model.entity.StatisticsInfoEntity;
import com.ben.domain.web.adapter.repository.IStatisticsRepository;
import com.ben.domain.web.service.IStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-04  12:03
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Slf4j
public class StatisticsServiceImpl implements IStatisticsService {

    @Autowired
    private IStatisticsRepository repository;
    @Override
    public StatisticsInfoEntity findStatistics() {
        return repository.findStatistics();
    }
}
