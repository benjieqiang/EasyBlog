package com.ben.infrastructure.persistent.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-14  20:42
 * @Description: 文章tag
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTagRel {

    /* id */
    private Long id;
    /* 文章id */
    private Long articleId;
    /* 标签id */
    private Long tagId;
}
