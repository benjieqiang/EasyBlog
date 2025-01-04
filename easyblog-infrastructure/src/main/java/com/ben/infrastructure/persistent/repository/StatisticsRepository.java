package com.ben.infrastructure.persistent.repository;

import com.ben.domain.web.model.entity.StatisticsInfoEntity;
import com.ben.domain.web.repository.IStatisticsRepository;
import com.ben.infrastructure.persistent.dao.IArticleDao;
import com.ben.infrastructure.persistent.dao.ICategoryDao;
import com.ben.infrastructure.persistent.dao.ITagDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-03  22:12
 * @Description: 展示仓储
 * @Version: 1.0
 */
@Slf4j
@Repository
public class StatisticsRepository implements IStatisticsRepository {
    @Autowired
    private IArticleDao articleDao;

    @Autowired
    private ITagDao tagDao;

    @Autowired
    private ICategoryDao categoryDao;


    @Override
    public StatisticsInfoEntity findStatistics() {
        // 1. 查询文章总数
        Long articleTotalCount = articleDao.selectCount();
        // 2. 查询分类总数
        Long categoryTotalCount = categoryDao.selectCount();

        // 3. 查询标签总数
        Long tagTotalCount = tagDao.selectCount();

        // 4. 总浏览量
        Long readNum = articleDao.selectAllReadNum();

        return StatisticsInfoEntity.builder()
                .articleTotalCount(articleTotalCount)
                .tagTotalCount(tagTotalCount)
                .categoryTotalCount(categoryTotalCount)
                .pvTotalCount(readNum)
                .build();
    }
}
