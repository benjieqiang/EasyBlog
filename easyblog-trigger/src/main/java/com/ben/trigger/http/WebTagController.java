package com.ben.trigger.http;

import com.ben.domain.web.model.entity.CategoryArticlePageEntity;
import com.ben.domain.web.model.entity.TagArticlePageEntity;
import com.ben.domain.web.model.entity.TagEntity;
import com.ben.domain.web.service.IWebTagService;
import com.ben.trigger.http.dto.category.FindWebCategoryArticlePageListReqDTO;
import com.ben.trigger.http.dto.category.FindWebCategoryArticlePageListRspDTO;
import com.ben.trigger.http.dto.tag.FindWebTagArticlePageListReqDTO;
import com.ben.trigger.http.dto.tag.FindWebTagArticlePageListRspDTO;
import com.ben.trigger.http.dto.tag.FindWebTagReqDTO;
import com.ben.trigger.http.dto.tag.FindWebTagRspDTO;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.response.PageResponse;
import com.ben.types.response.Response;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @CreateTime: 2024-12-13  10:49
 * @Description: 前台 标签模块
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/${app.config.api-version}/tag")
@Api(tags = "前台 标签模块")
public class WebTagController {

    @Autowired
    private IWebTagService tagService;


    @PostMapping("/list")
    @ApiOperation(value = "查询标签数据")
    @ApiOperationLog(description = "查询标签数据")
    public Response findTagSelectList(@RequestBody @Validated FindWebTagReqDTO request) {
        Long size = request.getSize();
        List<TagEntity> tagSelectList = tagService.findTagSelectList(size);
        List<FindWebTagRspDTO> webTagRspDTOS = null;
        if (!CollectionUtils.isEmpty(tagSelectList)) {
            webTagRspDTOS = tagSelectList.stream()
                    .map(tag -> FindWebTagRspDTO.builder()
                            .id(tag.getId())
                            .name(tag.getName())
                            .articlesTotal(tag.getArticlesTotal())
                            .build())
                    .collect(Collectors.toList());
        }
        return Response.success(webTagRspDTOS);
    }

    @PostMapping("/article/list")
    @ApiOperation(value = "获取标签下文章分页数据")
    @ApiOperationLog(description = "获取标签下文章分页数据")
    public PageResponse findTagArticlePageList(@RequestBody @Validated FindWebTagArticlePageListReqDTO request) {

        PageInfo<TagArticlePageEntity> tagArticlePageEntityPageInfo = tagService.findTagArticlePageList(request.getId(), request.getCurrent(), request.getSize());
        List<TagArticlePageEntity> tagArticlePageEntities = tagArticlePageEntityPageInfo.getList();
        List<FindWebTagArticlePageListRspDTO> rspDTOS = null;
        if (!CollectionUtils.isEmpty(tagArticlePageEntities)) {
            rspDTOS = tagArticlePageEntities.stream().map(categoryArticlePageEntity ->
                    FindWebTagArticlePageListRspDTO.builder()
                            .id(categoryArticlePageEntity.getId())
                            .title(categoryArticlePageEntity.getTitle())
                            .cover(categoryArticlePageEntity.getCover())
                            .createDate(categoryArticlePageEntity.getCreateDate())
                            .build()
            ).collect(Collectors.toList());
        }

        return PageResponse.success(
                tagArticlePageEntityPageInfo.getTotal(),
                tagArticlePageEntityPageInfo.getPageNum(),
                tagArticlePageEntityPageInfo.getSize(),
                rspDTOS
        );
    }
}