package com.ben.domain.web.adapter.repository;

import com.ben.domain.web.model.aggregate.CommentListAggregate;
import com.ben.domain.web.model.entity.CommentEntity;
import com.ben.domain.web.model.entity.QQUserInfoEntity;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-06  10:44
 * @Description: TODO
 * @Version: 1.0
 */
public interface ICommentRepository {
    QQUserInfoEntity findQQUserInfo(String qq);

    void publishComment(CommentEntity comment);

    CommentListAggregate findCommentList(String routerUrl);
}
