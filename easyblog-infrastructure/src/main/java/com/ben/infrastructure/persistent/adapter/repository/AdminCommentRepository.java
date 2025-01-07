package com.ben.infrastructure.persistent.adapter.repository;

import com.ben.domain.admin.event.UpdateCommentEvent;
import com.ben.domain.admin.model.entity.CommentEntity;
import com.ben.domain.admin.model.entity.CommentPageEntity;
import com.ben.domain.admin.repository.IAdminCommentRepository;
import com.ben.domain.web.model.valobj.CommentStatusVO;
import com.ben.infrastructure.persistent.dao.ICommentDao;
import com.ben.infrastructure.persistent.dao.po.ArticleCategoryRel;
import com.ben.infrastructure.persistent.dao.po.ArticleContent;
import com.ben.infrastructure.persistent.dao.po.Comment;
import com.ben.types.enums.ResponseCode;
import com.ben.types.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-06  10:43
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Component
public class AdminCommentRepository implements IAdminCommentRepository {
    @Autowired
    private ICommentDao commentDao;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public PageInfo<CommentEntity> findCommentPageList(CommentPageEntity commentPageEntity) {
        Integer current = commentPageEntity.getCurrent();
        Integer size = commentPageEntity.getSize();
        String routerUrl = commentPageEntity.getRouterUrl();
        LocalDateTime startDate = commentPageEntity.getStartDate();
        LocalDateTime endDate = commentPageEntity.getEndDate();

        Integer status = commentPageEntity.getStatus();
        PageHelper.startPage(current, size);
        List<Comment> comments = commentDao.selectPageList(routerUrl, startDate, endDate, null);

        // 若该分类下未发布任何文章
        if (CollectionUtils.isEmpty(comments)) {
            log.info("==> 评论无数据, routerUrl: {}", routerUrl);
            return new PageInfo<>(null);
        }
        PageInfo<Comment> commentPageInfo = new PageInfo<>(comments);
        List<Comment> commentPageInfoList = commentPageInfo.getList();
        List<CommentEntity> commentEntities = commentPageInfoList.stream().map(comment ->
                CommentEntity.builder()
                        .id(comment.getId())
                        .content(comment.getContent())
                        .avatar(comment.getAvatar())
                        .nickname(comment.getNickname())
                        .mail(comment.getMail())
                        .website(comment.getWebsite())
                        .routerUrl(comment.getRouterUrl())
                        .createTime(comment.getCreateTime())
                        .updateTime(comment.getUpdateTime())
                        .isDeleted(comment.getIsDeleted())
                        .replyCommentId(comment.getReplyCommentId())
                        .parentCommentId(comment.getParentCommentId())
                        .reason(comment.getReason())
                        .status(comment.getStatus())
                        .build()).collect(Collectors.toList());

        return new PageInfo(commentEntities);
    }

    @Override
    public void deleteComment(Long commentId) {
        transactionTemplate.execute(status -> {
            try {
                // 查询该评论是一级评论，还是二级评论
                Comment comment = commentDao.selectById(commentId);

                // 判断评论是否存在
                if (Objects.isNull(comment)) {
                    log.warn("该评论不存在, commentId: {}", commentId);
                    throw new BizException(ResponseCode.COMMENT_NOT_FOUND);
                }

                // 删除评论
                commentDao.deleteById(commentId);

                Long replyCommentId = comment.getReplyCommentId();

                // 一级评论
                if (Objects.isNull(replyCommentId)) {
                    // 删除子评论
                    commentDao.deleteByParentCommentId(commentId);
                } else {
                    // 二级评论：删除此评论, 以及此评论下的所有回复
                    deleteAllChildComment(commentId);
                }
                return 1;
            } catch (Exception e) {
                status.setRollbackOnly();
                log.error("删除评论失败", e);
                throw new BizException(ResponseCode.COMMENT_DELETE_FAILED);
            }
        });
    }

    @Override
    public void examine(Long commentId, Integer status, String reason) {
        // 根据提交的评论 ID 查询该条评论
        Comment comment = commentDao.selectById(commentId);

        // 判空
        if (Objects.isNull(comment)) {
            log.warn("该评论不存在, commentId: {}", commentId);
            throw new BizException(ResponseCode.COMMENT_NOT_FOUND);
        }

        // 评论当前状态
        Integer currStatus = comment.getStatus();

        // 若未处于待审核状态
        if (!Objects.equals(currStatus, CommentStatusVO.WAIT_EXAMINE.getCode())) {
            log.warn("该评论未处于待审核状态, commentId: {}", commentId);
            throw new BizException(ResponseCode.COMMENT_STATUS_NOT_WAIT_EXAMINE);
        }

        // 更新评论
        commentDao.update(Comment.builder()
                .id(commentId)
                .status(status)
                .reason(reason)
                .build());

        // 发送文章发布事件
        eventPublisher.publishEvent(new UpdateCommentEvent(this, commentId));
    }

    /**
     * 递归删除所有子评论
     *
     * @param commentId
     */
    private void deleteAllChildComment(Long commentId) {
        // 查询此评论的所有回复
        List<Comment> childCommentDOS = commentDao.selectByReplyCommentId(commentId);

        if (CollectionUtils.isEmpty(childCommentDOS))
            return;

        // 循环递归删除
        childCommentDOS.forEach(childCommentDO -> {
            Long childCommentId = childCommentDO.getId();

            commentDao.deleteById(childCommentId);
            // 递归调用
            deleteAllChildComment(childCommentId);
        });

    }
}
