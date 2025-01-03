package com.ben.trigger.http.dto.category;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author benjieqiang
 * @description 前端查询分类请求
 * @date 2024/12/12 3:32 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "前端查询分类请求 DTO")
public class FindWebCategoryReqDTO {
    /**
     * 展示数量
     */
    private Long size;
}
