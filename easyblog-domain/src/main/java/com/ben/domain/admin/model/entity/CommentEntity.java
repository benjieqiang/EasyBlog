package com.ben.domain.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-06  09:26
 * @Description: 评论
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {

    /* id */
    private Long id;
    /* 评论内容 */
    private String content;
    /* 头像 */
    private String avatar;
    /* 昵称 */
    private String nickname;
    /* 邮箱 */
    private String mail;
    /* 网站地址 */
    private String website;
    /* 评论所属的路由 */
    private String routerUrl;
    /* 创建时间 */
    private LocalDateTime createTime;
    /* 最后一次更新时间 */
    private LocalDateTime updateTime;
    /* 删除标志位：0：未删除 1：已删除 */
    private Integer isDeleted;
    /* 回复的评论 ID */
    private Integer replyCommentId;
    /* 父评论 ID */
    private Integer parentCommentId;
    /* 原因描述 */
    private String reason;
    /* 1: 待审核；2：正常；3：审核未通过; */
    private Integer status;
}
