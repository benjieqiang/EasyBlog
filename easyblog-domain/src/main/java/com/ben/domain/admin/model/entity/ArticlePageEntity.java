package com.ben.domain.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-30  20:48
 * @Description: 文章分页查询对象
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePageEntity {
    Integer pageNum;
    Integer pageSize;
    /**
     * 文章标题
     */
    private String title;

    /**
     * 发布的起始日期
     */
    private LocalDateTime startDate;

    /**
     * 发布的结束日期
     */
    private LocalDateTime endDate;

    /**
     * 文章类型
     */
    private Integer type = 1;
}
