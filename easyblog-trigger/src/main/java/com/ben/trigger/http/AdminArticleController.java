package com.ben.trigger.http;

import com.ben.domain.admin.model.aggregate.ArticleDetailAggregate;
import com.ben.domain.admin.model.entity.ArticleEntity;
import com.ben.domain.admin.model.entity.ArticlePageEntity;
import com.ben.domain.admin.service.IAdminArticleService;
import com.ben.trigger.http.dto.article.*;
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
 * @CreateTime: 2024-12-30  16:52
 * @Description: Admin文章模块
 * @Version: 1.0
 */
@RestController
@Slf4j
@Api(tags = "Admin 文章模块")
@RequestMapping("/api/${app.config.api-version}/admin/article")
public class AdminArticleController {

    @Autowired
    private IAdminArticleService articleService;

    @PostMapping("/publish")
    @ApiOperation(value = "文章发布")
    @ApiOperationLog(description = "文章发布")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response publishArticle(@RequestBody @Validated PublishArticleReqDTO request) {
        ArticleEntity articleEntity = ArticleEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .cover(request.getCover())
                .summary(request.getSummary())
                .categoryId(request.getCategoryId())
                .tags(request.getTags())
                .build();
        try {
            articleService.publishArticle(articleEntity);
        } catch (Exception e) {
            return Response.fail(ResponseCode.ARTICLE_PUBLISH_FAILED);
        }
        return Response.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "文章删除")
    @ApiOperationLog(description = "文章删除")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response deleteArticle(@RequestBody @Validated DeleteArticleReqDTO request) {
        try {
            Long articleId = request.getId();
            articleService.deleteArticle(articleId);
        } catch (Exception e) {
            return Response.fail(ResponseCode.ARTICLE_DELETE_FAILED);
        }
        return Response.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "查询文章分页数据")
    @ApiOperationLog(description = "查询文章分页数据")
    public PageResponse findArticlePageList(@RequestBody @Validated FindArticlePageListReqDTO request) {
        ArticlePageEntity articlePageEntity = ArticlePageEntity.builder()
                .pageNum(request.getCurrent())
                .pageSize(request.getSize())
                .title(request.getTitle())
                .startDate(Objects.nonNull(request.getStartDate()) ? request.getStartDate().atStartOfDay() : null)
                .endDate(Objects.nonNull(request.getEndDate()) ? request.getEndDate().atStartOfDay() : null)
                .type(request.getType())
                .build();

        PageInfo<ArticleEntity> articlePageList = articleService.findArticlePageList(articlePageEntity);
        List<ArticleEntity> articleEntities = articlePageList.getList();
        List<FindArticlePageListRspDTO> articlePageListRspDTOS = articleEntities.stream().map(
                articleEntity -> FindArticlePageListRspDTO.builder()
                        .id(articleEntity.getArticleId())
                        .title(articleEntity.getTitle())
                        .createTime(articleEntity.getCreateTime())
                        .cover(articleEntity.getCover())
                        .isTop(articleEntity.getIsTop())
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
        ArticleDetailAggregate articleDetail = articleService.findArticleDetail(articleId);
        FindArticleDetailRspDTO articleDetailRspDTO = FindArticleDetailRspDTO.builder()
                .id(articleDetail.getArticleId())
                .title(articleDetail.getTitle())
                .cover(articleDetail.getCover())
                .content(articleDetail.getContent())
                .summary(articleDetail.getSummary())
                .categoryId(articleDetail.getCategoryId())
                .tagIds(articleDetail.getTagIds())
                .build();
        return Response.success(articleDetailRspDTO);
    }
}
