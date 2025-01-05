package com.ben.trigger.http.dto.wiki;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author: benjieqiang
 * @Date: 2025-01-05  09:56
 * @Version: v1.0
 * @Description: 删除知识库
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "删除知识库 DTO")
public class DeleteWikiReqDTO {

    @NotNull(message = "知识库 ID 不能为空")
    private Long id;
}