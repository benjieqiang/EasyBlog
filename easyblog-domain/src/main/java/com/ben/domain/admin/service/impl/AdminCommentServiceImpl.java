package com.ben.domain.admin.service.impl;

import com.ben.domain.admin.model.entity.CommentEntity;
import com.ben.domain.admin.model.entity.CommentPageEntity;
import com.ben.domain.admin.repository.IAdminCommentRepository;
import com.ben.domain.admin.service.IAdminCommentService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-06  10:46
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Slf4j
public class AdminCommentServiceImpl implements IAdminCommentService {
    @Autowired
    private IAdminCommentRepository commentRepository;
    @Override
    public PageInfo<CommentEntity> findCommentPageList(CommentPageEntity commentPageEntity) {
        return commentRepository.findCommentPageList(commentPageEntity);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteComment(commentId);
    }

    @Override
    public void examine(Long commentId, Integer status, String reason) {
        commentRepository.examine(commentId, status, reason);
    }
}
