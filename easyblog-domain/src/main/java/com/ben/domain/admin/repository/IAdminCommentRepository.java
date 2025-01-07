package com.ben.domain.admin.repository;

import com.ben.domain.admin.model.entity.CommentEntity;
import com.ben.domain.admin.model.entity.CommentPageEntity;
import com.github.pagehelper.PageInfo;

/**
 * @InterfaceName: IAdminCommentRepository
 * @Description: 添加描述
 * @Author: benjieqiang
 * @LastChangeDate: 2025/1/6 10:43 AM
 * @Version: v1.0
 */

public interface IAdminCommentRepository {
    PageInfo<CommentEntity> findCommentPageList(CommentPageEntity commentPageEntity);

    void deleteComment(Long id);

    void examine(Long commentId, Integer status, String reason);
}

