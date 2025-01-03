package com.ben.trigger.http.dto.archieve;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-03  11:27
 * @Description: 查询文章归类数据出参
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询文章归类数据出参 DTO")
public class FindArchiveArticlePageListRspDTO {
    /**
     * 归档的月份
     */
    private YearMonth month;

    private List<FindArchiveArticleRspDTO> articles;

}
