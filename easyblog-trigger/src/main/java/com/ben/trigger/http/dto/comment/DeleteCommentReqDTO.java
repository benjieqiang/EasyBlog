package com.ben.trigger.http.dto.comment;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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
@ApiModel(value = "删除评论 DTO")
public class DeleteCommentReqDTO {

    @NotNull(message = "评论 ID 不能为空")
    private Long id;
}
