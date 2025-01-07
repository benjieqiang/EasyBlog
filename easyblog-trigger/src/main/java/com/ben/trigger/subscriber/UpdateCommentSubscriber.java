package com.ben.trigger.subscriber;

import com.ben.domain.admin.event.UpdateCommentEvent;
import com.ben.domain.web.model.valobj.CommentStatusVO;
import com.ben.infrastructure.persistent.dao.IBlogSettingDao;
import com.ben.infrastructure.persistent.dao.ICommentDao;
import com.ben.infrastructure.persistent.dao.po.BlogSetting;
import com.ben.infrastructure.persistent.dao.po.Comment;
import com.ben.types.utils.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-07  10:37
 * @Description: 后台管理-更新评论状态从待审核-》审核通过/不通过，发邮件通知
 * @Version: 1.0
 */
@Component
@Slf4j
public class UpdateCommentSubscriber implements ApplicationListener<UpdateCommentEvent> {

    @Autowired
    private ICommentDao commentDao;
    @Autowired
    private IBlogSettingDao blogSettingDao;
    @Autowired
    private MailUtil mailUtil;

    @Value("${blog.domain}")
    private String blogDomain;

    @Override
    @Async("threadPoolTaskExecutor")
    public void onApplicationEvent(UpdateCommentEvent event) {
        Long commentId = event.getCommentId();
        // 获取当前线程名称
        String threadName = Thread.currentThread().getName();

        log.info("==> threadName: {}", threadName);
        log.info("==> 评论更新事件消费成功，commentId: {}", commentId);
        // 1. 获取该评论详情
        Comment comment = commentDao.selectById(commentId);
        Long replyCommentId = comment.getReplyCommentId();
        String nickname = comment.getNickname();
        String content = comment.getContent();
        Integer status = comment.getStatus();
        String mail = comment.getMail();
        String routerUrl = comment.getRouterUrl();

        // 2. 获取博客作者
        BlogSetting blogSetting = blogSettingDao.selectBlogSetting(1L);
        String blogName = blogSetting.getName();
        // 3. 判断评论状态
        // 若审核不通过，通知发评论的用户，你的评论未被博主审核通过，原因是什么
        if (Objects.equals(status, CommentStatusVO.EXAMINE_FAILED.getCode())
                && StringUtils.isNotBlank(mail)) {

            String reason = comment.getReason();
            String title = String.format("你在%s的评论未被审核通过", blogName);

            String html = String.format("<html><body>" +
                            "<h2>你的评论:</h2><p>%s</p>" +
                            "<h2>审核未通过原因:</h2><p>%s</p>" +
                            "<p><a href='%s%s' target='_blank'>查看详情</a></p>" +
                            "</body></html>",
                    content, reason, blogDomain, routerUrl);
            mailUtil.sendHtml(mail, title, html);
        } else if (Objects.equals(status, CommentStatusVO.NORMAL.getCode())) {
            // 如果是审核通过，通知发评论的用户，你的评论已经被博主审核通过
            String title = String.format("你在%s的评论已被审核通过", blogName);
            String html = String.format("<html><body>" +
                            "<h2>你的评论:</h2><p>%s</p>" +
                            "<p><a href='%s%s' target='_blank'>查看详情</a></p>" +
                            "</body></html>",
                    content, blogDomain, routerUrl);
            mailUtil.sendHtml(mail, title, html);

            // 4. 通知被评论人，提示评论被回复了
            notifyBeCommentedUser(replyCommentId, blogName, nickname, content, blogDomain);
        }

    }

    /**
     * 邮件通知被回复的用户
     *
     * @param replyCommentId
     * @param blogName
     * @param nickname
     * @param content
     * @param domain
     */
    private void notifyBeCommentedUser(Long replyCommentId, String blogName, String nickname, String content, String domain) {
        if (Objects.isNull(replyCommentId))
            return;

        // 被回复的评论
        Comment replyComment = commentDao.selectById(replyCommentId);

        // 邮箱地址
        String to = replyComment.getMail();

        // 邮箱判空
        if (StringUtils.isBlank(to))
            return;

        String routerUrl = replyComment.getRouterUrl();
        String title = String.format("你在%s的评论收到了回复", blogName);

        String html = String.format("<html><body>" +
                        "<h2>你的评论:</h2><p>%s</p>" +
                        "<h2>%s 回复了你:</h2><p>%s</p>" +
                        "<p><a href='%s%s' target='_blank'>查看详情</a></p>" +
                        "</body></html>",
                replyComment.getContent(), nickname, content, domain, routerUrl);
        mailUtil.sendHtml(to, title, html);
    }
}
