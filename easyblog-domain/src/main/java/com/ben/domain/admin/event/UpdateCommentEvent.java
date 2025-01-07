package com.ben.domain.admin.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UpdateCommentEvent extends ApplicationEvent {

    /**
     * 评论ID
     */
    private Long commentId;

    public UpdateCommentEvent(Object source, Long commentId) {
        super(source);
        this.commentId = commentId;
    }
}