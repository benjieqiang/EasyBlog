package com.ben.domain.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-14  20:42
 * @Description: 文章正文
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleContentEntity {

    /* id */
    private Long id;
    /* 文章id */
    private Long articleId;
    /* 文章正文 */
    private String content;
}
