package com.ben.trigger.http;

import com.ben.domain.admin.model.entity.CategoryEntity;
import com.ben.domain.admin.model.entity.CategoryPageEntity;
import com.ben.domain.admin.model.entity.TagEntity;
import com.ben.domain.admin.model.entity.TagPageEntity;
import com.ben.domain.admin.service.IAdminTagService;
import com.ben.trigger.http.dto.category.FindCategoryPageListRspDTO;
import com.ben.trigger.http.dto.common.SelectRspDTO;
import com.ben.trigger.http.dto.tag.*;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.enums.ResponseCode;
import com.ben.types.response.PageResponse;
import com.ben.types.response.Response;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * @CreateTime: 2024-12-13  10:49
 * @Description: Admin 标签模块
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/${app.config.api-version}/admin/tag")
@Api(tags = "Admin 标签模块")
public class AdminTagController {

    @Autowired
    private IAdminTagService tagService;

    @PostMapping("/add")
    @ApiOperation(value = "添加标签")
    @ApiOperationLog(description = "添加标签")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response addTags(@RequestBody @Validated AddTagReqDTO addTagReqDTO) {
        List<String> tags = addTagReqDTO.getTags();
        try {
            tagService.addTags(tags);
        } catch (Exception e) {
            return Response.fail(ResponseCode.TAG_CANT_DUPLICATE);
        }
        return Response.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "标签分页数据获取")
    @ApiOperationLog(description = "标签分页数据获取")
    public PageResponse findTagPageList(@RequestBody @Validated FindTagPageListReqDTO findTagPageListReqDTO) {
        TagPageEntity tagPageEntity = TagPageEntity.builder()
                .pageNum(findTagPageListReqDTO.getCurrent())
                .pageSize(findTagPageListReqDTO.getSize())
                .name(findTagPageListReqDTO.getName())
                .startDate(findTagPageListReqDTO.getStartDate())
                .endDate(findTagPageListReqDTO.getEndDate())
                .build();
        PageInfo<TagEntity> tagPageEntityPageInfo = tagService.findTagPageList(tagPageEntity);

        List<FindTagPageListRspDTO> findCategoryPageListRspDTOS = tagPageEntityPageInfo.getList().stream()
                .map(entity -> FindTagPageListRspDTO.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .createTime(entity.getCreateTime())
                        .articlesTotal(entity.getArticlesTotal())
                        .build()).collect(Collectors.toList());

        return PageResponse.success(tagPageEntityPageInfo.getTotal(), tagPageEntityPageInfo.getPageNum(), tagPageEntityPageInfo.getSize(), findCategoryPageListRspDTOS);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除标签")
    @ApiOperationLog(description = "删除标签")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response deleteTag(@RequestBody @Validated DeleteTagReqDTO deleteTagReqDTO) {
        int count = tagService.deleteTag(deleteTagReqDTO.getId());
        if (count == 0) {
            return Response.fail(ResponseCode.TAG_NOT_EXISTED);
        }
        return Response.success();
    }

    @PostMapping("/search")
    @ApiOperation(value = "标签模糊查询")
    @ApiOperationLog(description = "标签模糊查询")
    public Response searchTags(@RequestBody @Validated SearchTagsReqDTO searchTagsReqDTO) {
        List<TagEntity> tagEntities = tagService.searchTags(searchTagsReqDTO.getKey());
        List<SelectRspDTO> selectRspDTOS = null;
        if (!CollectionUtils.isEmpty(tagEntities)) {
            selectRspDTOS = tagEntities.stream()
                    .map(tag -> SelectRspDTO.builder()
                            .label(tag.getName())
                            .value(String.valueOf(tag.getId()))
                            .build())
                    .collect(Collectors.toList());
        }
        return Response.success(selectRspDTOS);
    }

    @PostMapping("/select/list")
    @ApiOperation(value = "查询标签 Select 列表数据")
    @ApiOperationLog(description = "查询标签 Select 列表数据")
    public Response findTagSelectList() {
        List<TagEntity> tagSelectList = tagService.findTagSelectList();
        List<SelectRspDTO> selectRspDTOS = null;
        if (!CollectionUtils.isEmpty(tagSelectList)) {
            selectRspDTOS = tagSelectList.stream()
                    .map(tag -> SelectRspDTO.builder()
                            .label(tag.getName())
                            .value(String.valueOf(tag.getId()))
                            .build())
                    .collect(Collectors.toList());
        }
        return Response.success(selectRspDTOS);
    }

}