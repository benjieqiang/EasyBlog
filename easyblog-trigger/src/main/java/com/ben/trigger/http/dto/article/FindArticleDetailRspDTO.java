package com.ben.trigger.http.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-30  20:32
 * @Description: 查询文章详情 RSP DTO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindArticleDetailRspDTO {

    /**
     * 文章 ID
     */
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章封面
     */
    private String cover;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 分类 ID
     */
    private Long categoryId;

    /**
     * 标签 ID 集合
     */
    private List<Long> tagIds;

    /**
     * 摘要
     */
    private String summary;

}
