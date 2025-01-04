package com.ben.domain.admin.service;

/**
 * @InterfaceName: IAdminStatisticsService
 * @Description: 添加描述
 * @Author: benjieqiang
 * @LastChangeDate: 2025/1/4 5:48 PM
 * @Version: v1.0
 */

public interface IAdminStatisticsService {
    /**
     * 统计各分类下文章总数
     */
    void statisticsCategoryArticleTotal();

    /**
     * 统计各标签下文章总数
     */
    void statisticsTagArticleTotal();
}

