package com.ben.trigger.http;

import com.ben.domain.web.model.aggregate.ArticleSearchAggregate;
import com.ben.domain.web.model.aggregate.WebArticleDetailAggregate;
import com.ben.domain.web.model.entity.ArticleEntity;
import com.ben.domain.web.model.entity.ArticleSearchEntity;
import com.ben.domain.web.model.entity.IndexArticlePageEntity;
import com.ben.domain.web.model.entity.TagEntity;
import com.ben.domain.web.service.IArticleService;
import com.ben.domain.web.service.ISearchService;
import com.ben.trigger.http.dto.article.*;
import com.ben.trigger.http.dto.search.SearchArticlePageListReqDTO;
import com.ben.trigger.http.dto.search.SearchArticlePageListRspDTO;
import com.ben.trigger.http.dto.tag.FindIndexTagRspDTO;
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

    @Autowired
    private ISearchService searchService;

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
                        .createTime(articleEntity.getCreateTime().toLocalDate())
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
        WebArticleDetailAggregate articleDetail = articleService.findArticleDetail(articleId);
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


    @PostMapping("/search")
    @ApiOperation(value = "文章搜索")
    @ApiOperationLog(description = "文章搜索")
    public PageResponse searchArticlePageList(@RequestBody @Validated SearchArticlePageListReqDTO request) {
        int current = request.getCurrent();
        int size = request.getSize();
        String word = request.getWord();
        ArticleSearchAggregate articleSearchAggregate = searchService.searchArticlePageList(word, current, size);
        List<ArticleSearchEntity> articleEntityList = articleSearchAggregate.getArticleEntityList();

        List<SearchArticlePageListRspDTO> dtos = null;
        if (!CollectionUtils.isEmpty(articleEntityList)) {
            dtos = articleEntityList.stream().map(articleEntity ->
                    SearchArticlePageListRspDTO.builder()
                            .id(articleEntity.getId())
                            .cover(articleEntity.getCover())
                            .title(articleEntity.getTitle())
                            .summary(articleEntity.getSummary())
                            .createDate(articleEntity.getCreateDate())
                            .build()).collect(Collectors.toList());
        }

        return PageResponse.success(
                articleSearchAggregate.getTotal(),
                articleSearchAggregate.getCurrent(),
                articleSearchAggregate.getSize(),
                dtos);
    }
}
