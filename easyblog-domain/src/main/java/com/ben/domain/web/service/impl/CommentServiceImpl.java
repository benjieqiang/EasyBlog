package com.ben.domain.web.service.impl;

import com.ben.domain.web.model.aggregate.CommentListAggregate;
import com.ben.domain.web.model.entity.CommentEntity;
import com.ben.domain.web.model.entity.QQUserInfoEntity;
import com.ben.domain.web.adapter.repository.ICommentRepository;
import com.ben.domain.web.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-06  10:46
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private ICommentRepository repository;

    @Override
    public QQUserInfoEntity findQQUserInfo(String qq) {
        return repository.findQQUserInfo(qq);
    }

    @Override
    public void publishComment(CommentEntity comment) {
        repository.publishComment(comment);
    }

    @Override
    public CommentListAggregate findCommentList(String routerUrl) {
        return repository.findCommentList(routerUrl);
    }
}
