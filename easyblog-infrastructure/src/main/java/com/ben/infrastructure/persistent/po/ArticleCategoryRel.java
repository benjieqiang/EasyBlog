package com.ben.infrastructure.persistent.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-14  20:42
 * @Description: 文章分类
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCategoryRel {

    /* id */
    private Long id;
    /* 文章id */
    private Long articleId;
    /* 分类id */
    private Long categoryId;
}
