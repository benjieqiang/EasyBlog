package com.ben.trigger.http.dto.tag;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author benjieqiang
 * @description 删除标签
 * @date 2024/12/13 10:53 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "删除标签 DTO")
public class DeleteTagReqDTO {

    @NotNull(message = "标签 ID 不能为空")
    private Long id;

}
