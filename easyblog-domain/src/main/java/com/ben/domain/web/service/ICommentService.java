package com.ben.domain.web.service;

import com.ben.domain.web.model.aggregate.CommentListAggregate;
import com.ben.domain.web.model.entity.CommentEntity;
import com.ben.domain.web.model.entity.QQUserInfoEntity;

/**
 * @InterfaceName: ICommentService
 * @Description: 添加描述
 * @Author: benjieqiang
 * @LastChangeDate: 2025/1/6 10:46 AM
 * @Version: v1.0
 */

public interface ICommentService {
    QQUserInfoEntity findQQUserInfo(String qq);

    void publishComment(CommentEntity comment);

    CommentListAggregate findCommentList(String routerUrl);
}

