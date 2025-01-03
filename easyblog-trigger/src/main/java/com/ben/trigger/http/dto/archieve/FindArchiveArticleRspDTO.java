package com.ben.trigger.http.dto.archieve;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-03  11:29
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询文章归类 DTO")
public class FindArchiveArticleRspDTO {
    private Long id;
    private String cover;
    private String title;
    /**
     * 发布日期
     */
    private LocalDate createDate;

    /**
     * 发布的月份（此字段不需要展示在前端，主要用于按月份分组使用）
     */
    private YearMonth createMonth;
}
