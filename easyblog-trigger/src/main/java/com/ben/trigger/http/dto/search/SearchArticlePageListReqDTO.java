package com.ben.trigger.http.dto.search;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "文章搜索 DTO")
public class SearchArticlePageListReqDTO {
    /**
     * 查询关键词
     */
    @NotBlank(message = "搜索关键词不能为空")
    private String word;

    /**
     * 当前页码, 默认第一页
     */
    private Integer current = 1;
    /**
     * 每页展示的数据数量，默认每页展示 10 条数据
     */
    private Integer size = 10;
}
