package com.ben.domain.web.adapter.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;


/**
 * @Author: benjieqiang
 * @CreateTime: 2025/1/6 10:10 PM
 * @Description: 发布评论事件
 * @Version: 1.0
 */

@Getter
public class PublishCommentEvent extends ApplicationEvent {

    /**
     * 评论 ID
     */
    private Long commentId;

    public PublishCommentEvent(Object source, Long commentId) {
        super(source);
        this.commentId = commentId;
    }
}
