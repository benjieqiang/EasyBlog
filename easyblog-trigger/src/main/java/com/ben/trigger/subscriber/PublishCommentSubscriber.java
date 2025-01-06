package com.ben.trigger.subscriber;

import com.ben.domain.web.adapter.event.PublishCommentEvent;
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
 * @CreateTime: 2025-01-06  22:14
 * @Description: 监控前端发布评论事件
 * @Version: 1.0
 */
@Component
@Slf4j
public class PublishCommentSubscriber implements ApplicationListener<PublishCommentEvent> {
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
    public void onApplicationEvent(PublishCommentEvent event) {
        // 在这里处理收到的事件，可以是任何逻辑操作
        Long commentId = event.getCommentId();

        // 获取当前线程名称
        String threadName = Thread.currentThread().getName();

        log.info("==> threadName: {}", threadName);
        log.info("==> 评论发布事件消费成功，commentId: {}", commentId);

        Comment comment = commentDao.selectById(commentId);
        Long replyCommentId = comment.getReplyCommentId();
        String nickname = comment.getNickname();
        String content = comment.getContent();
        Integer status = comment.getStatus();

        // 获取博客设置相关信息
        BlogSetting blogSetting = blogSettingDao.selectBlogSetting(1L);
        // 博客名称
        String blogName = blogSetting.getName();
        // 博主邮箱
        String authorMail = blogSetting.getMail();
        // 是否需要审核
        boolean isCommentExamineOpen = blogSetting.getIsCommentExamineOpen();
        // 是否开启了敏感词过滤
        boolean isSensWordOpen = blogSetting.getIsCommentSensiWordOpen();

        // 如果管理员开启了审核，插入表的评论状态是待审核，此时，无法发送邮件。二级评论，并且状态为 “正常”, 邮件通知被评论的用户
        if (Objects.nonNull(replyCommentId) && Objects.equals(status, CommentStatusVO.NORMAL.getCode())) {
            Comment replyComment = commentDao.selectById(replyCommentId);

            String to = replyComment.getMail();

            String routerUrl = replyComment.getRouterUrl();
            String title = String.format("你在%s的评论收到了回复", blogName);

            // 构建 HTML
            String html = String.format("<html><body>" + "<h2>你的评论:</h2><p>%s</p>" + "<h2>%s 回复了你:</h2><p>%s</p>" + "<p><a href='%s%s' target='_blank'>查看详情</a></p>" + "</body></html>", replyComment.getContent(), nickname, content, blogDomain, routerUrl);
            // 发送邮件
            mailUtil.sendHtml(to, title, html);
        } else if (Objects.isNull(replyCommentId) && StringUtils.isNotBlank(authorMail)) {
            // 一级评论, 需要通知到博主
            String routerUrl = comment.getRouterUrl();
            String title = String.format("%s收到了评论", blogName);

            // 如果开启了评论审核，并且当前评论状态为 "待审核"，标题后缀添加【待审核】标识
            if (isCommentExamineOpen && Objects.equals(status, CommentStatusVO.WAIT_EXAMINE.getCode())) {
                title = title + "【待审核】";
            }

            // 如果开启了敏感词过滤，并且当前评论状态为 "审核不通过"，标题后缀添加【系统已拦截】标识
            if (isSensWordOpen && Objects.equals(status, CommentStatusVO.EXAMINE_FAILED.getCode())) {
                title = title + "【系统已拦截】";
            }

            // 构建 HTML
            String html = String.format("<html><body>" + "<h2>路由:</h2><p>%s</p>" + "<h2>%s 评论了你:</h2><p>%s</p>" + "<p><a href='%s%s' target='_blank'>查看详情</a></p>" + "</body></html>", routerUrl, nickname, comment.getContent(), blogDomain, routerUrl);
            // 发送邮件
            mailUtil.sendHtml(authorMail, title, html);
        }

    }
}
