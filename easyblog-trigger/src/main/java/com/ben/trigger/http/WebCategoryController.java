package com.ben.trigger.http;

import com.ben.domain.web.model.entity.CategoryEntity;
import com.ben.domain.web.service.ICategoryService;
import com.ben.trigger.http.dto.common.SelectRspDTO;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-02  17:54
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@Slf4j
@Api(tags = "前台 标签模块")
@RequestMapping("/api/${app.config.api-version}/category")
public class WebCategoryController {
    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/select/list")
    @ApiOperation(value = "获取标签分类数据")
    @ApiOperationLog(description = "获取标签分类数据")
    public Response findCategorySelectList() {
        List<CategoryEntity> categorySelectList = categoryService.findCategorySelectList();

        List<SelectRspDTO> selectRspDTOS = null;
        if (!CollectionUtils.isEmpty(categorySelectList)) {
            selectRspDTOS = categorySelectList.stream()
                    .map(category -> SelectRspDTO.builder()
                            .label(category.getName())
                            .value(String.valueOf(category.getId()))
                            .build())
                    .collect(Collectors.toList());
        }
        return Response.success(selectRspDTOS);
    }
}
