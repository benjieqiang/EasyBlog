package com.ben.trigger.http.dto.article;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-30  20:43
 * @Description: 查询文章分页数据入参
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询文章分页数据入参 DTO")
public class FindArticlePageListReqDTO {

    /**
     * 文章标题
     */
    private String title;

    /**
     * 发布的起始日期
     */
    private LocalDate startDate;

    /**
     * 发布的结束日期
     */
    private LocalDate endDate;

    /**
     * 文章类型
     */
    private Integer type;

    /**
     * 当前页码, 默认第一页
     */
    private Integer current = 1;
    /**
     * 每页展示的数据数量，默认每页展示 10 条数据
     */
    private Integer size = 10;
}

