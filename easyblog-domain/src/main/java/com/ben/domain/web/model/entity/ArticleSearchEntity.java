package com.ben.domain.web.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-04  16:44
 * @Description: 文章搜索实体
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSearchEntity {
    /**
     * 文章 ID
     */
    private Long id;
    /**
     * 封面
     */
    private String cover;
    /**
     * 标题
     */
    private String title;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 发布日期
     */
    private String createDate;
}
