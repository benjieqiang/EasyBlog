package com.ben.trigger.http.dto.comment;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-07  08:52
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询评论分页数据入参 DTO")
public class FindCommentPageListReqDTO {

    /**
     * 路由地址
     */
    private String routerUrl;

    /**
     * 发布的起始日期
     */
    private LocalDate startDate;

    /**
     * 发布的结束日期
     */
    private LocalDate endDate;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 当前页码, 默认第一页
     */
    private Integer current = 1;
    /**
     * 每页展示的数据数量，默认每页展示 10 条数据
     */
    private Integer size = 10;
}