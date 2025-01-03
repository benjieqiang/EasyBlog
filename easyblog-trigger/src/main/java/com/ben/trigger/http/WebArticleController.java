package com.ben.trigger.http;

import com.ben.domain.web.model.aggregate.IndexArticleDetailAggregate;
import com.ben.domain.web.model.entity.ArticleEntity;
import com.ben.domain.web.model.entity.IndexArticlePageEntity;
import com.ben.domain.web.model.entity.TagEntity;
import com.ben.domain.web.service.IArticleService;
import com.ben.trigger.http.dto.article.*;
import com.ben.trigger.http.dto.common.SelectRspDTO;
import com.ben.trigger.http.dto.tag.FindIndexTagRspDTO;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.response.PageResponse;
import com.ben.types.response.Response;
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
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-02  17:54
 * @Description: 文章模块
 * @Version: 1.0
 */
@RestController
@Slf4j
@Api(tags = "前台 文章模块")
@RequestMapping("/api/${app.config.api-version}/article")
public class WebArticleController {

    @Autowired
    private IArticleService articleService;

    @PostMapping("/list")
    @ApiOperation(value = "查询文章分页数据")
    @ApiOperationLog(description = "查询文章分页数据")
    public PageResponse findArticlePageList(@RequestBody @Validated FindIndexArticlePageListReqDTO request) {
        Integer current = request.getCurrent();
        Integer size = request.getSize();
        PageInfo<IndexArticlePageEntity> articlePageList = articleService.findArticlePageList(current, size);
        List<IndexArticlePageEntity> articleEntities = articlePageList.getList();

        List<FindIndexArticlePageListRspDTO> articlePageListRspDTOS = articleEntities.stream().map(
                articleEntity -> FindIndexArticlePageListRspDTO.builder()
                        .id(articleEntity.getId())
                        .cover(articleEntity.getCover())
                        .title(articleEntity.getTitle())
                        .createTime(articleEntity.getCreateTime())
                        .categoryId(articleEntity.getCategoryId())
                        .categoryName(articleEntity.getCategoryName())
                        .tags(articleEntity.getTags().stream().map(
                                        tagEntity -> FindIndexTagRspDTO.builder()
                                                .id(tagEntity.getId())
                                                .name(tagEntity.getName())
                                                .build())
                                .collect(Collectors.toList()))
                        .build()
        ).collect(Collectors.toList());

        return PageResponse.success(
                articlePageList.getTotal(),
                articlePageList.getPageNum(),
                articlePageList.getSize(),
                articlePageListRspDTOS
        );
    }

    @PostMapping("/detail")
    @ApiOperation(value = "查询文章详情")
    @ApiOperationLog(description = "查询文章详情")
    public Response findArticleDetail(@RequestBody @Validated FindArticleDetailReqDTO request) {
        Long articleId = request.getId();
        IndexArticleDetailAggregate articleDetail = articleService.findArticleDetail(articleId);
        List<TagEntity> tags = articleDetail.getTags();
        List<FindIndexTagRspDTO> tagRspDTOS = tags.stream()
                .map(tag -> FindIndexTagRspDTO.builder()
                        .id(tag.getId())
                        .name(tag.getName())
                        .build())
                .collect(Collectors.toList());

        ArticleEntity preArticle = articleDetail.getPreArticle();
        FindIndexPreNextArticleRspDTO preArticleRspDTO = null;
        if (Objects.nonNull(preArticle)) {
            preArticleRspDTO = FindIndexPreNextArticleRspDTO.builder()
                    .articleId(preArticle.getArticleId())
                    .articleTitle(preArticle.getTitle())
                    .build();
        }

        ArticleEntity nextArticle = articleDetail.getNextArticle();
        FindIndexPreNextArticleRspDTO nextArticleRspDTO = null;
        if (Objects.nonNull(nextArticle)) {
            nextArticleRspDTO = FindIndexPreNextArticleRspDTO.builder()
                    .articleId(nextArticle.getArticleId())
                    .articleTitle(nextArticle.getTitle())
                    .build();
        }

        FindIndexArticleDetailRspDTO articleDetailRsp = FindIndexArticleDetailRspDTO.builder()
                .title(articleDetail.getTitle())
                .content(articleDetail.getContent())
                .createTime(articleDetail.getCreateTime())
                .categoryId(articleDetail.getCategoryId())
                .categoryName(articleDetail.getCategoryName())
                .readNum(articleDetail.getReadNum())
                .tags(tagRspDTOS)
                .preArticle(preArticleRspDTO)
                .nextArticle(nextArticleRspDTO)
                .totalWords(articleDetail.getTotalWords())
                .readTime(articleDetail.getReadTime())
                .updateTime(articleDetail.getUpdateTime())
                .build();
        return Response.success(articleDetailRsp);
    }

}
