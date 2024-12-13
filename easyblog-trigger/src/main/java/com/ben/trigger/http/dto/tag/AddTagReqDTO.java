package com.ben.trigger.http.dto.tag;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author benjieqiang
 * @description 标签新增
 * @date 2024/12/13 10:53 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "添加标签 DTO")
public class AddTagReqDTO {

    @NotEmpty(message = "标签集合不能为空")
    private List<String> tags;

}
