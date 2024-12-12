package com.ben.trigger.http;

import com.ben.domain.admin.model.entity.CategoryEntity;
import com.ben.domain.admin.model.entity.CategoryPageEntity;
import com.ben.domain.admin.service.IAdminCategoryService;
import com.ben.trigger.http.dto.category.DeleteCategoryReqDTO;
import com.ben.trigger.http.dto.category.FindCategoryPageListReqDTO;
import com.ben.trigger.http.dto.category.FindCategoryPageListRspDTO;
import com.ben.trigger.http.dto.category.SelectRspDTO;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.enums.ResponseCode;
import com.ben.types.response.PageResponse;
import com.ben.types.response.Response;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-12  15:11
 * @Description: Admin 标签管理模块
 * @Version: 1.0
 */
@RestController
@Slf4j
@Api(tags = "Admin 标签管理模块")
@RequestMapping("/api/${app.config.api-version}/admin/category")
public class AdminCategoryController {

    @Autowired
    private IAdminCategoryService categoryService;

    @PostMapping("/add")
    @ApiOperation(value = "添加分类")
    @ApiOperationLog(description = "添加分类")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response addCategory(String name) {
        try {
            categoryService.addCategory(name);
        } catch (Exception e) {
            return Response.fail(ResponseCode.CATEGORY_NAME_IS_EXISTED);
        }
        return Response.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "获取分类分页数据")
    @ApiOperationLog(description = "分类分页数据获取")
    public PageResponse findCategoryPageList(@RequestBody @Validated FindCategoryPageListReqDTO findCategoryPageListReqDTO) {
        CategoryPageEntity categoryPageEntity = CategoryPageEntity.builder()
                .pageNum(findCategoryPageListReqDTO.getCurrent())
                .pageSize(findCategoryPageListReqDTO.getSize())
                .name(findCategoryPageListReqDTO.getName())
                .startDate(findCategoryPageListReqDTO.getStartDate())
                .endDate(findCategoryPageListReqDTO.getEndDate())
                .build();
        PageInfo<CategoryEntity> categoryEntityPageInfo = categoryService.findCategoryPageList(categoryPageEntity);

        List<CategoryEntity> categoryEntityList = categoryEntityPageInfo.getList();

        List<FindCategoryPageListRspDTO> findCategoryPageListRspDTOS = categoryEntityList.stream()
                .map(entity -> FindCategoryPageListRspDTO.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .createTime(entity.getCreateTime())
                        .articlesTotal(entity.getArticlesTotal())
                        .build()).collect(Collectors.toList());

        return PageResponse.success(categoryEntityPageInfo.getTotal(), categoryEntityPageInfo.getPageNum(), categoryEntityPageInfo.getSize(), findCategoryPageListRspDTOS);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除分类")
    @ApiOperationLog(description = "删除分类")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response deleteCategory(@RequestBody @Validated DeleteCategoryReqDTO deleteCategoryReqDTO) {
        int count = categoryService.deleteCategory(deleteCategoryReqDTO.getId());
        if (count == 0) {
            return Response.fail(ResponseCode.TAG_NOT_EXISTED);
        }
        return Response.success();
    }

    @PostMapping("/select/list")
    @ApiOperation(value = "获取分类 Select 下拉列表数据")
    @ApiOperationLog(description = "获取分类 Select 下拉列表数据")
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
