package com.ben.domain.web.model.entity;

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
    /* 回复的评论 ID */
    private Long replyCommentId;
    /* 父评论 ID */
    private Long parentCommentId;
}
