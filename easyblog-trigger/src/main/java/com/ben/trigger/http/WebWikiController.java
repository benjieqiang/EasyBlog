package com.ben.trigger.http;

import com.ben.domain.web.model.aggregate.WebWikiCatalogAggregate;
import com.ben.domain.web.model.aggregate.WikiPreNextArticleAggregate;
import com.ben.domain.web.model.entity.ArticleEntity;
import com.ben.domain.web.model.entity.WikiInfoEntity;
import com.ben.domain.web.service.IWikiService;
import com.ben.trigger.http.dto.article.FindIndexPreNextArticleRspDTO;
import com.ben.trigger.http.dto.wiki.*;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.enums.ResponseCode;
import com.ben.types.response.Response;
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
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-05  09:54
 * @Description: 知识库模块
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/${app.config.api-version}/wiki")
@Api(tags = "前端 wiki模块")
public class WebWikiController {
    @Autowired
    private IWikiService wikiService;

    @PostMapping("/list")
    @ApiOperation(value = "获取知识库数据")
    @ApiOperationLog(description = "获取知识库数据")
    public Response findWikiList() {
        List<WikiInfoEntity> wikiInfoEntities = wikiService.findWikiList();
        if (CollectionUtils.isEmpty(wikiInfoEntities)) return Response.fail(ResponseCode.WIKI_NOT_EXISTED);
        List<FindWebWikiListRspDTO> wikiListRspDTOS = wikiInfoEntities.stream().map(wikiInfoEntity ->
                FindWebWikiListRspDTO.builder()
                        .id(wikiInfoEntity.getId())
                        .title(wikiInfoEntity.getTitle())
                        .cover(wikiInfoEntity.getCover())
                        .summary(wikiInfoEntity.getSummary())
                        .isTop(wikiInfoEntity.getIsTop())
                        .firstArticleId(wikiInfoEntity.getFirstArticleId())
                        .build()).collect(Collectors.toList());

        return Response.success(wikiListRspDTOS);
    }

    @PostMapping("/catalog/list")
    @ApiOperation(value = "查询知识库目录")
    @ApiOperationLog(description = "查询知识库目录")
    public Response findWikiCatalogList(@RequestBody @Validated FindWebWikiCatalogListReqDTO request) {
        List<WebWikiCatalogAggregate> aggregates = wikiService.findWikiCatalogList(request.getId());
        if (CollectionUtils.isEmpty(aggregates)) return Response.fail(ResponseCode.WIKI_CATALOG_NOT_EXISTED);
        List<FindWikiCatalogListRspDTO> rspDTOS = aggregates.stream().map(wikiCatalogAggregate ->
                FindWikiCatalogListRspDTO.builder()
                        .id(wikiCatalogAggregate.getId())
                        .articleId(wikiCatalogAggregate.getArticleId())
                        .title(wikiCatalogAggregate.getTitle())
                        .level(wikiCatalogAggregate.getLevel())
                        .sort(wikiCatalogAggregate.getSort())
                        .children(wikiCatalogAggregate.getChildren().stream().map(
                                level1Cataglog -> FindWikiCatalogListRspDTO.builder()
                                        .id(level1Cataglog.getId())
                                        .articleId(level1Cataglog.getArticleId())
                                        .title(level1Cataglog.getTitle())
                                        .level(level1Cataglog.getLevel())
                                        .sort(level1Cataglog.getSort())
                                        .build()
                        ).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList());

        return Response.success(rspDTOS);
    }


    @PostMapping("/article/preNext")
    @ApiOperation(value = "获取知识库文章上下页")
    @ApiOperationLog(description = "获取知识库文章上下页")
    public Response findArticlePreNext(@RequestBody FindWebWikiArticlePreNextReqDTO request) {
        WikiPreNextArticleAggregate articleAggregate = wikiService.findArticlePreNext(request.getId(), request.getArticleId());
        ArticleEntity preArticle = articleAggregate.getPreArticle();
        FindIndexPreNextArticleRspDTO preArticleRspDTO = null;
        if (Objects.nonNull(preArticle)) {
            preArticleRspDTO = FindIndexPreNextArticleRspDTO.builder()
                    .articleId(preArticle.getArticleId())
                    .articleTitle(preArticle.getTitle())
                    .build();
        }

        ArticleEntity nextArticle = articleAggregate.getNextArticle();
        FindIndexPreNextArticleRspDTO nextArticleRspDTO = null;
        if (Objects.nonNull(nextArticle)) {
            nextArticleRspDTO = FindIndexPreNextArticleRspDTO.builder()
                    .articleId(nextArticle.getArticleId())
                    .articleTitle(nextArticle.getTitle())
                    .build();
        }
        return Response.success(FindWebWikiArticlePreNextRspDTO.builder()
                .preArticle(preArticleRspDTO)
                .nextArticle(nextArticleRspDTO)
                .build());
    }

}
