package com.ben.trigger.http.dto.tag;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @description 标签模糊查询
 * @author benjieqiang
 * @date 2024/12/13 10:53 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "标签模糊查询 DTO")
public class SearchTagsReqDTO {

    @NotBlank(message = "标签查询关键词不能为空")
    private String key;

}
