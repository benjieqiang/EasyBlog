package com.ben.trigger.http.dto.category;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author benjieqiang
 * @description 删除分类
 * @date 2024/12/12 3:32 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "删除分类 DTO")
public class DeleteCategoryReqDTO {

    @NotNull(message = "分类 ID 不能为空")
    private Long id;

}
