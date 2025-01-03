package com.ben.trigger.http;

import com.ben.domain.web.model.entity.CategoryArticlePageEntity;
import com.ben.domain.web.model.entity.CategoryEntity;
import com.ben.domain.web.service.ICategoryService;
import com.ben.trigger.http.dto.category.FindWebCategoryArticlePageListReqDTO;
import com.ben.trigger.http.dto.category.FindWebCategoryArticlePageListRspDTO;
import com.ben.trigger.http.dto.category.FindWebCategoryReqDTO;
import com.ben.trigger.http.dto.category.FindWebCategoryRspDTO;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.response.PageResponse;
import com.ben.types.response.Response;
import com.github.pagehelper.PageInfo;
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

    @PostMapping("/article/list")
    @ApiOperation(value = "获取分类下文章分页数据")
    @ApiOperationLog(description = "获取分类下文章分页数据")
    public PageResponse findCategoryArticlePageList(@RequestBody @Validated FindWebCategoryArticlePageListReqDTO request) {


        PageInfo<CategoryArticlePageEntity> categoryArticlePageList = categoryService.findCategoryArticlePageList(request.getId(), request.getCurrent(), request.getSize());
        List<CategoryArticlePageEntity> categoryArticlePageEntities = categoryArticlePageList.getList();
        List<FindWebCategoryArticlePageListRspDTO> rspDTOS = null;
        if (!CollectionUtils.isEmpty(categoryArticlePageEntities)) {
            rspDTOS = categoryArticlePageEntities.stream().map(categoryArticlePageEntity ->
                    FindWebCategoryArticlePageListRspDTO.builder()
                            .id(categoryArticlePageEntity.getId())
                            .title(categoryArticlePageEntity.getTitle())
                            .cover(categoryArticlePageEntity.getCover())
                            .createDate(categoryArticlePageEntity.getCreateDate())
                            .build()
            ).collect(Collectors.toList());
        }

        return PageResponse.success(
                categoryArticlePageList.getTotal(),
                categoryArticlePageList.getPageNum(),
                categoryArticlePageList.getSize(),
                rspDTOS
        );
    }

}
