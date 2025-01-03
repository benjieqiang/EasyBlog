package com.ben.trigger.http;

import com.ben.domain.web.model.entity.CategoryEntity;
import com.ben.domain.web.service.ICategoryService;
import com.ben.trigger.http.dto.category.FindWebCategoryReqDTO;
import com.ben.trigger.http.dto.category.FindWebCategoryRspDTO;
import com.ben.trigger.http.dto.common.SelectRspDTO;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-02  17:54
 * @Description: 前台 分类模块
 * @Version: 1.0
 */
@RestController
@Slf4j
@Api(tags = "前台 分类模块")
@RequestMapping("/api/${app.config.api-version}/category")
public class WebCategoryController {
    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/list")
    @ApiOperation(value = "获取标签分类数据")
    @ApiOperationLog(description = "获取标签分类数据")
    public Response findCategorySelectList(@RequestBody @Validated FindWebCategoryReqDTO request) {
        Long size = request.getSize();
        List<CategoryEntity> categorySelectList = categoryService.findCategorySelectList(size);

        List<FindWebCategoryRspDTO> categoryRspDTOS = null;
        if (!CollectionUtils.isEmpty(categorySelectList)) {
            categoryRspDTOS = categorySelectList.stream()
                    .map(entity -> FindWebCategoryRspDTO.builder()
                            .id(entity.getId())
                            .name(entity.getName())
                            .articlesTotal(entity.getArticlesTotal())
                            .build())
                    .collect(Collectors.toList());
        }
        return Response.success(categoryRspDTOS);
    }
}
