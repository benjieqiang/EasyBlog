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
 * @Description: 删除文章 DTO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "删除文章 DTO")
public class DeleteArticleReqDTO {

    @NotNull(message = "文章 ID 不能为空")
    private Long id;
}
