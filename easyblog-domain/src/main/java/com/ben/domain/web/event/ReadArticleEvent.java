package com.ben.domain.web.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-04  11:41
 * @Description: 阅读文章事件
 * @Version: 1.0
 */
@Getter
public class ReadArticleEvent extends ApplicationEvent {

    /**
     * 文章 ID
     */
    private Long articleId;

    public ReadArticleEvent(Object source, Long articleId) {
        super(source);
        this.articleId = articleId;
    }
}
