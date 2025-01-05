package com.ben.domain.web.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: benjieqiang
 * @Date: 2025-01-05  09:56
 * @Version: v1.0
 * @Description: TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WikiInfoEntity {

    /**
     * 知识库 ID
     */
    private Long id;

    /**
     * 知识库标题
     */
    private String title;

    /**
     * 知识库封面
     */
    private String cover;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 是否置顶
     */
    private Boolean isTop;

    /**
     * 目录中首篇文章id
     */
    private Long firstArticleId;
}