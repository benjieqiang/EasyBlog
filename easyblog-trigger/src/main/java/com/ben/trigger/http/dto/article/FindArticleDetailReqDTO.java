package com.ben.trigger.http.dto.article;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-30  20:32
 * @Description: 查询文章详情 DTO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询文章详情 DTO")
public class FindArticleDetailReqDTO {

    @NotNull(message = "文章 ID 不能为空")
    private Long id;
}
