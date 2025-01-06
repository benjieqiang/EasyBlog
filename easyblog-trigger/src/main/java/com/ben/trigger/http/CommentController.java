package com.ben.trigger.http;

import com.ben.domain.web.model.aggregate.CommentListAggregate;
import com.ben.domain.web.model.entity.CommentEntity;
import com.ben.domain.web.model.entity.CommentItemEntity;
import com.ben.domain.web.model.entity.QQUserInfoEntity;
import com.ben.domain.web.service.ICommentService;
import com.ben.trigger.http.dto.comment.*;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.enums.ResponseCode;
import com.ben.types.response.Response;
import com.github.houbb.heaven.util.util.CollectionUtil;
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
 * @CreateTime: 2025-01-06  10:47
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@Slf4j
@Api(tags = "前台 评论模块")
@RequestMapping("/api/${app.config.api-version}/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @PostMapping("/qq/userInfo")
    @ApiOperation(value = "获取 QQ 用户信息")
    @ApiOperationLog(description = "获取 QQ 用户信息")
    public Response findQQUserInfo(@RequestBody @Validated FindQQUserInfoReqDTO request) {
        QQUserInfoEntity userInfoEntity = commentService.findQQUserInfo(request.getQq());
        return Response.success(FindQQUserInfoRspDTO.builder()
                .nickname(userInfoEntity.getNickname())
                .mail(userInfoEntity.getMail())
                .avatar(userInfoEntity.getAvatar())
                .build());
    }

    @PostMapping("/publish")
    @ApiOperation(value = "发布评论")
    @ApiOperationLog(description = "发布评论")
    public Response publishComment(@RequestBody @Validated PublishCommentReqDTO request) {
        CommentEntity commentEntity = CommentEntity.builder()
                .avatar(request.getAvatar())
                .mail(request.getMail())
                .content(request.getContent())
                .nickname(request.getNickname())
                .routerUrl(request.getRouterUrl())
                .website(request.getWebsite())
                .replyCommentId(request.getReplyCommentId())
                .parentCommentId(request.getParentCommentId())
                .build();
        commentService.publishComment(commentEntity);

        return Response.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "获取页面所有评论")
    @ApiOperationLog(description = "获取页面所有评论")
    public Response findPageComments(@RequestBody FindCommentListReqDTO request) {
        CommentListAggregate aggregate = commentService.findCommentList(request.getRouterUrl());

        List<CommentItemEntity> commentEntities = aggregate.getCommentEntities();
        if (CollectionUtil.isEmpty(commentEntities)) return Response.fail(ResponseCode.COMMENT_NOT_FOUND);
        List<FindCommentItemRspDTO> commentItemRspDTOS = commentEntities.stream().map(comment ->
                FindCommentItemRspDTO.builder()
                        .id(comment.getId())
                        .avatar(comment.getAvatar())
                        .nickname(comment.getNickname())
                        .website(comment.getWebsite())
                        .content(comment.getContent())
                        .createTime(comment.getCreateTime())
                        .childComments(comment.getChildComments().stream().map(childComment ->
                                FindCommentItemRspDTO.builder()
                                        .id(childComment.getId())
                                        .avatar(childComment.getAvatar())
                                        .nickname(childComment.getNickname())
                                        .website(childComment.getWebsite())
                                        .content(childComment.getContent())
                                        .replyNickname(childComment.getReplyNickname())
                                        .createTime(childComment.getCreateTime()).build()).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList());
        return Response.success(FindCommentListRspDTO.builder()
                .total(aggregate.getTotal())
                .comments(commentItemRspDTOS)
                .build());
    }

}
