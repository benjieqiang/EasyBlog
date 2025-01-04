package com.ben.domain.admin.repository;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-04  17:49
 * @Description: TODO
 * @Version: 1.0
 */
public interface IAdminStatisticsRepository {
    /**
     * 统计各分类下文章总数
     */
    void statisticsCategoryArticleTotal();

    /**
     * 统计各标签下文章总数
     */
    void statisticsTagArticleTotal();
}
