package com.ben.domain.admin.service;

import com.ben.domain.admin.model.entity.CommentEntity;
import com.ben.domain.admin.model.entity.CommentPageEntity;
import com.github.pagehelper.PageInfo;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-06  10:46
 * @Description: TODO
 * @Version: 1.0
 */
public interface IAdminCommentService {
    PageInfo<CommentEntity> findCommentPageList(CommentPageEntity commentPageEntity);

    void deleteComment(Long commentId);

    void examine(Long commentId, Integer status, String reason);
}
