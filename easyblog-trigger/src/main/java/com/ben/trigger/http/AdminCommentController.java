package com.ben.trigger.http;

import com.ben.domain.admin.model.entity.CommentEntity;
import com.ben.domain.admin.model.entity.CommentPageEntity;
import com.ben.domain.admin.service.IAdminCommentService;
import com.ben.trigger.http.dto.comment.DeleteCommentReqDTO;
import com.ben.trigger.http.dto.comment.ExamineCommentReqDTO;
import com.ben.trigger.http.dto.comment.FindCommentPageListReqDTO;
import com.ben.trigger.http.dto.comment.FindCommentPageListRspDTO;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.enums.ResponseCode;
import com.ben.types.response.PageResponse;
import com.ben.types.response.Response;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * @CreateTime: 2025-01-06  10:47
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@Slf4j
@Api(tags = "Admin 评论模块")
@RequestMapping("/api/${app.config.api-version}/admin/comment")
public class AdminCommentController {
    @Autowired
    private IAdminCommentService commentService;

    @PostMapping("/list")
    @ApiOperation(value = "查询评论分页数据")
    @ApiOperationLog(description = "查询评论分页数据")
    public Response findCommentPageList(@RequestBody @Validated FindCommentPageListReqDTO request) {
        CommentPageEntity commentPageEntity = CommentPageEntity.builder()
                .routerUrl(request.getRouterUrl())
                .startDate(Objects.nonNull(request.getStartDate()) ? request.getStartDate().atStartOfDay() : null)
                .endDate(Objects.nonNull(request.getEndDate()) ? request.getEndDate().atStartOfDay() : null)
                .status(request.getStatus())
                .current(request.getCurrent())
                .size(request.getSize())
                .build();
        PageInfo<CommentEntity> commentPageList = commentService.findCommentPageList(commentPageEntity);
        List<CommentEntity> commentEntities = commentPageList.getList();
        List<FindCommentPageListRspDTO> dtos = Lists.newArrayList();
        if (CollectionUtils.isEmpty(commentEntities)) return Response.fail(ResponseCode.COMMENT_NOT_FOUND);
        dtos = commentEntities.stream().map(commentEntity ->
                FindCommentPageListRspDTO.builder()
                        .id(commentEntity.getId())
                        .routerUrl(commentEntity.getRouterUrl())
                        .avatar(commentEntity.getAvatar())
                        .nickname(commentEntity.getNickname())
                        .mail(commentEntity.getMail())
                        .website(commentEntity.getWebsite())
                        .createTime(commentEntity.getCreateTime())
                        .content(commentEntity.getContent())
                        .status(commentEntity.getStatus())
                        .reason(commentEntity.getReason())
                        .build()).collect(Collectors.toList());

        return PageResponse.success(
                commentPageList.getTotal(),
                commentPageList.getPageNum(),
                commentPageList.getSize(),
                dtos
        );
    }

    @PostMapping("/delete")
    @ApiOperation(value = "评论删除")
    @ApiOperationLog(description = "评论删除")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response deleteComment(@RequestBody @Validated DeleteCommentReqDTO request) {
        commentService.deleteComment(request.getId());
        return Response.success();
    }

    @PostMapping("/examine")
    @ApiOperation(value = "评论审核")
    @ApiOperationLog(description = "评论审核")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response examinePass(@RequestBody @Validated ExamineCommentReqDTO request) {
        Long commentId = request.getId();
        Integer status = request.getStatus();
        String reason = request.getReason();

        commentService.examine(commentId, status, reason);

        return Response.success();
    }

}
