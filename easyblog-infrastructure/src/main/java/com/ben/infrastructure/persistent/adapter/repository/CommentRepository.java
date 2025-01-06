package com.ben.infrastructure.persistent.adapter.repository;

import com.ben.domain.web.adapter.port.IQQInfoPort;
import com.ben.domain.web.adapter.repository.ICommentRepository;
import com.ben.domain.web.model.aggregate.CommentListAggregate;
import com.ben.domain.web.model.entity.CommentEntity;
import com.ben.domain.web.model.entity.CommentItemEntity;
import com.ben.domain.web.model.entity.QQUserInfoEntity;
import com.ben.domain.web.model.valobj.CommentStatusVO;
import com.ben.infrastructure.persistent.dao.IBlogSettingDao;
import com.ben.infrastructure.persistent.dao.ICommentDao;
import com.ben.infrastructure.persistent.dao.po.BlogSetting;
import com.ben.infrastructure.persistent.dao.po.Comment;
import com.ben.types.enums.ResponseCode;
import com.ben.types.exception.BizException;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-06  10:43
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Component
public class CommentRepository implements ICommentRepository {

    @Autowired
    private IQQInfoPort qqInfoPort;

    @Autowired
    private ICommentDao commentDao;

    @Autowired
    private IBlogSettingDao blogSettingDao;

    @Resource
    private SensitiveWordBs sensitiveWordBs;

    @Override
    public QQUserInfoEntity findQQUserInfo(String qq) {
        return qqInfoPort.findQQUserInfo(qq);
    }

    @Override
    public void publishComment(CommentEntity commentEntity) {
        // 回复的评论 ID
        Long replyCommentId = commentEntity.getReplyCommentId();
        // 评论内容
        String content = commentEntity.getContent();
        // 昵称
        String nickname = commentEntity.getNickname();

        // 查询博客设置相关信息（约定的 ID 为 1）
        BlogSetting blogSetting = blogSettingDao.selectBlogSetting(1L);
        // 是否开启了敏感词过滤
        boolean isCommentSensiWordOpen = blogSetting.getIsCommentSensiWordOpen();
        // 是否开启了审核
        boolean isCommentExamineOpen = blogSetting.getIsCommentExamineOpen();

        // 设置默认状态（正常）
        Integer status = CommentStatusVO.NORMAL.getCode();
        // 审核不通过原因
        String reason = "";

        // 如果开启了审核, 设置状态为待审核，等待博主后台审核通过
        if (isCommentExamineOpen) {
            status = CommentStatusVO.WAIT_EXAMINE.getCode();
        }


        // 评论内容是否包含敏感词
        boolean isContainSensitiveWord = false;
        // 是否开启了敏感词过滤
        if (isCommentSensiWordOpen) {
            // 校验评论中是否包含敏感词
            isContainSensitiveWord = sensitiveWordBs.contains(content);

            if (isContainSensitiveWord) {
                // 若包含敏感词，设置状态为审核不通过
                status = CommentStatusVO.EXAMINE_FAILED.getCode();
                // 匹配到的所有敏感词组
                List<String> keywords = sensitiveWordBs.findAll(content);
                // 生成原因
                reason = String.format("系统自动拦截，包含敏感词：%s", keywords);
                log.warn("此评论内容中包含敏感词: {}, content: {}", keywords, content);
            }
        }

        // 构建 DO 对象
        Comment comment = Comment.builder()
                .avatar(commentEntity.getAvatar())
                .content(content)
                .mail(commentEntity.getMail())
                .nickname(nickname)
                .routerUrl(commentEntity.getRouterUrl())
                .website(commentEntity.getWebsite())
                .replyCommentId(replyCommentId)
                .parentCommentId(commentEntity.getParentCommentId())
                .status(status)
                .reason(reason)
                .build();

        // 新增评论
        commentDao.insert(comment);

//        Long commentId = commentDO.getId();
//        // 发送评论发布事件
//        eventPublisher.publishEvent(new PublishCommentEvent(this, commentId));

        // 给予前端对应的提示信息
        if (isContainSensitiveWord)
            throw new BizException(ResponseCode.COMMENT_CONTAIN_SENSITIVE_WORD);

        if (Objects.equals(status, CommentStatusVO.WAIT_EXAMINE.getCode()))
            throw new BizException(ResponseCode.COMMENT_WAIT_EXAMINE);
    }

    @Override
    public CommentListAggregate findCommentList(String routerUrl) {
        // 1. 查询该路由地址下状态正常的所有评论
        List<Comment> comments = commentDao.selectByRouterUrlAndStatus(routerUrl, CommentStatusVO.NORMAL.getCode());
        // 2. 总评论数
        Integer total = comments.size();

        List<CommentItemEntity> commentItemEntities = null;
        // 3. 查询一级评论: parentCommentId 父级 ID 为空，则表示为一级评论
        if (!CollectionUtils.isEmpty(comments)) {
            commentItemEntities = comments.stream()
                    .filter(comment -> Objects.isNull(comment.getParentCommentId()))
                    .map(comment -> CommentItemEntity.builder()
                            .id(comment.getId())
                            .avatar(comment.getAvatar())
                            .nickname(comment.getNickname())
                            .website(comment.getWebsite())
                            .content(comment.getContent())
                            .createTime(comment.getCreateTime())
                            .build())
                    .collect(Collectors.toList());

            // 4. 遍历一级评论，再遍历所有评论，过滤一级评论的子评论(子评论的ParentCommentId是一级评论的id)，循环设置评论回复数据
            commentItemEntities.forEach(entity -> {
                Long commentId = entity.getId();
                // PO -> Entity
                List<CommentItemEntity> childComments = comments.stream()
                        .filter(comment -> Objects.equals(comment.getParentCommentId(), commentId)) // 过滤出一级评论下所有子评论
                        .sorted(Comparator.comparing(Comment::getCreateTime)) // 按发布时间升序排列
                        .map(comment -> {
                            // 组建entity实体列表，
                            CommentItemEntity childComment = CommentItemEntity.builder()
                                    .id(comment.getId())
                                    .avatar(comment.getAvatar())
                                    .nickname(comment.getNickname())
                                    .website(comment.getWebsite())
                                    .content(comment.getContent())
                                    .createTime(comment.getCreateTime()).build();
                            Long replyCommentId = comment.getReplyCommentId();
                            // 若二级评论的 replayCommentId 不等于一级评论 ID, 前端则需要展示【回复 @ xxx】，需要设置回复昵称
                            if (!Objects.equals(replyCommentId, commentId)) {
                                // 设置回复用户的昵称
                                Optional<Comment> optionalComment = comments.stream()
                                        .filter(comment1 -> Objects.equals(comment1.getId(), replyCommentId)).findFirst();
                                optionalComment.ifPresent(value -> childComment.setReplyNickname(value.getNickname()));
                            }
                            return childComment;
                        }).collect(Collectors.toList());
                // 往一级评论中set子评论
                entity.setChildComments(childComments);
            });
        }


        return CommentListAggregate.builder()
                .total(total)
                .commentEntities(commentItemEntities)
                .build();
    }
}
