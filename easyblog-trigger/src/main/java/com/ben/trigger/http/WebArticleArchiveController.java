package com.ben.trigger.http;

import com.ben.domain.web.model.aggregate.ArchiveArticleAggregate;
import com.ben.domain.web.service.IArchiveService;
import com.ben.trigger.http.dto.archieve.FindArchiveArticlePageListReqDTO;
import com.ben.trigger.http.dto.archieve.FindArchiveArticlePageListRspDTO;
import com.ben.trigger.http.dto.archieve.FindArchiveArticleRspDTO;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.response.PageResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-03  11:25
 * @Description: 文章归档
 * @Version: 1.0
 */
@RestController
@Slf4j
@Api(tags = "前台 文章归档")
@RequestMapping("/api/${app.config.api-version}/archive")
public class WebArticleArchiveController {

    @Autowired
    private IArchiveService archiveService;

    @PostMapping("/list")
    @ApiOperation(value = "获取文章归档分页数据")
    @ApiOperationLog(description = "获取文章归档分页数据")
    public PageResponse findArchivePageList(@RequestBody @Validated FindArchiveArticlePageListReqDTO request) {
        Integer current = request.getCurrent();
        Integer size = request.getSize();
        PageInfo<ArchiveArticleAggregate> archivePageList = archiveService.findArchivePageList(current, size);
        List<ArchiveArticleAggregate> articleAggregates = archivePageList.getList();

        List<FindArchiveArticlePageListRspDTO> findArchiveArticlePageListRspDTOS = articleAggregates.stream().map(archiveArticleAggregate ->
                FindArchiveArticlePageListRspDTO.builder()
                        .month(archiveArticleAggregate.getMonth())
                        .articles(archiveArticleAggregate.getArticles().stream().map(archiveArticleEntity -> FindArchiveArticleRspDTO.builder()
                                .id(archiveArticleEntity.getId())
                                .title(archiveArticleEntity.getTitle())
                                .cover(archiveArticleEntity.getCover())
                                .createDate(archiveArticleEntity.getCreateDate())
                                .createMonth(archiveArticleEntity.getCreateMonth())
                                .build()).collect(Collectors.toList()))
                        .build()
        ).collect(Collectors.toList());

        return PageResponse.success(
                archivePageList.getTotal(),
                archivePageList.getPageNum(),
                archivePageList.getSize(),
                findArchiveArticlePageListRspDTOS
        );
    }

}