package com.ben.trigger.http;

import com.ben.domain.admin.model.aggregate.WikiCatalogAggregate;
import com.ben.domain.admin.model.aggregate.WikiPageAggregate;
import com.ben.domain.admin.model.entity.WikiEntity;
import com.ben.domain.admin.model.entity.WikiPageEntity;
import com.ben.domain.admin.service.IAdminWikiService;
import com.ben.trigger.http.dto.wiki.*;
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

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-05  09:54
 * @Description: Admin 知识库模块
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/${app.config.api-version}/admin/wiki")
@Api(tags = "Admin 知识库模块")
public class AdminWikiController {
    @Autowired
    private IAdminWikiService wikiService;

    @PostMapping("/add")
    @ApiOperation(value = "新增知识库")
    @ApiOperationLog(description = "新增知识库")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response addWiki(@RequestBody @Validated AddWikiReqDTO request) {
        try {
            String title = request.getTitle();
            String summary = request.getSummary();
            String cover = request.getCover();
            wikiService.addWiki(title, summary, cover);
        } catch (Exception e) {
            return Response.fail(ResponseCode.INSERT_FAILED);
        }
        return Response.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "知识库删除")
    @ApiOperationLog(description = "知识库删除")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response deleteWiki(@RequestBody @Validated DeleteWikiReqDTO request) {
        int count = wikiService.deleteWiki(request.getId());
        if (count == 0) {
            return Response.fail(ResponseCode.WIKI_NOT_EXISTED);
        }
        return Response.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "查询知识库分页数据")
    @ApiOperationLog(description = "查询知识库分页数据")
    public Response findWikiPageList(@RequestBody @Validated FindWikiPageListReqDTO request) {
        WikiPageEntity wikiPageEntity = WikiPageEntity.builder()
                .title(request.getTitle())
                .startDate(Objects.nonNull(request.getStartDate()) ? request.getStartDate().atStartOfDay() : null)
                .endDate(Objects.nonNull(request.getEndDate()) ? request.getEndDate().atStartOfDay() : null)
                .current(request.getCurrent())
                .size(request.getSize())
                .build();
        WikiPageAggregate wikiPageAggregate = wikiService.findWikiPageList(wikiPageEntity);
        List<WikiEntity> wikiEntities = wikiPageAggregate.getWikiEntities();
        if (CollectionUtils.isEmpty(wikiEntities)) return Response.fail(ResponseCode.WIKI_NOT_EXISTED);

        List<FindWikiPageListRspDTO> wikiPageListRspDTOS = wikiEntities.stream().map(wikiEntity ->
                FindWikiPageListRspDTO.builder()
                        .id(wikiEntity.getId())
                        .title(wikiEntity.getTitle())
                        .cover(wikiEntity.getCover())
                        .summary(wikiEntity.getSummary())
                        .isPublish(wikiEntity.getIsPublish())
                        .createTime(wikiEntity.getCreateTime())
                        .isTop(wikiEntity.getIsPublish())
                        .build()).collect(Collectors.toList());
        return PageResponse.success(
                wikiPageAggregate.getTotal(),
                wikiPageAggregate.getCurrent(),
                wikiPageAggregate.getSize(),
                wikiPageListRspDTOS
        );
    }

    @PostMapping("/isTop/update")
    @ApiOperation(value = "更新知识库置顶状态")
    @ApiOperationLog(description = "更新知识库置顶状态")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response updateWikiIsTop(@RequestBody @Validated UpdateWikiIsTopReqDTO request) {
        wikiService.updateWikiIsTop(request.getId(), request.getIsTop());
        return Response.success();
    }

    @PostMapping("/isPublish/update")
    @ApiOperation(value = "更新知识库发布状态")
    @ApiOperationLog(description = "更新知识库发布状态")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response updateWikiIsPublish(@RequestBody @Validated UpdateWikiIsPublishReqDTO request) {
        wikiService.updateWikiIsPublish(request.getId(), request.getIsPublish());
        return Response.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新知识库")
    @ApiOperationLog(description = "更新知识库")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response updateWiki(@RequestBody @Validated UpdateWikiReqDTO request) {
        Long id = request.getId();
        String title = request.getTitle();
        String cover = request.getCover();
        String summary = request.getSummary();
        wikiService.updateWiki(id, title, cover, summary);
        return Response.success();
    }

    @PostMapping("/catalog/list")
    @ApiOperation(value = "查询知识库目录")
    @ApiOperationLog(description = "查询知识库目录")
    public Response findWikiCatalogList(@RequestBody @Validated FindWikiCatalogListReqDTO request) {
        List<WikiCatalogAggregate> aggregates = wikiService.findWikiCatalogList(request.getId());
        if (CollectionUtils.isEmpty(aggregates)) return Response.fail(ResponseCode.WIKI_CATALOG_NOT_EXISTED);
        List<FindWikiCatalogListRspDTO> rspDTOS = aggregates.stream().map(wikiCatalogAggregate ->
                FindWikiCatalogListRspDTO.builder()
                        .id(wikiCatalogAggregate.getId())
                        .articleId(wikiCatalogAggregate.getArticleId())
                        .title(wikiCatalogAggregate.getTitle())
                        .level(wikiCatalogAggregate.getLevel())
                        .sort(wikiCatalogAggregate.getSort())
                        .editing(wikiCatalogAggregate.getEditing())
                        .children(wikiCatalogAggregate.getChildren().stream().map(
                                level1Cataglog -> FindWikiCatalogListRspDTO.builder()
                                        .id(level1Cataglog.getId())
                                        .articleId(level1Cataglog.getArticleId())
                                        .title(level1Cataglog.getTitle())
                                        .level(level1Cataglog.getLevel())
                                        .sort(level1Cataglog.getSort())
                                        .editing(level1Cataglog.getEditing())
                                        .build()
                        ).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList());

        return Response.success(rspDTOS);
    }

    @PostMapping("/catalog/update")
    @ApiOperation(value = "更新知识库目录")
    @ApiOperationLog(description = "更新知识库目录")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response updateWikiCatalogs(@RequestBody @Validated UpdateWikiCatalogReqDTO request) {
        Long wikiId = request.getId();
        List<UpdateWikiCatalogItemReqDTO> catalogItemReqDTOS = request.getCatalogs();

        List<WikiCatalogAggregate> catalogAggregates = catalogItemReqDTOS.stream().map(catalog ->
                WikiCatalogAggregate.builder()
                        .id(catalog.getId())
                        .articleId(catalog.getArticleId())
                        .title(catalog.getTitle())
                        .level(catalog.getLevel())
                        .sort(catalog.getSort())
                        .children(catalog.getChildren().stream().map(
                                level1Cataglog -> WikiCatalogAggregate.builder()
                                        .id(level1Cataglog.getId())
                                        .articleId(level1Cataglog.getArticleId())
                                        .title(level1Cataglog.getTitle())
                                        .level(level1Cataglog.getLevel())
                                        .sort(level1Cataglog.getSort())
                                        .build()
                        ).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList());
        try {
            wikiService.updateWikiCatalogs(wikiId, catalogAggregates);
        } catch (Exception e) {
            return Response.fail(ResponseCode.WIKI_CATALOG_UPDATED_FAILED);
        }
        return Response.success();
    }
}
