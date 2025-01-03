package com.ben.domain.web.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-30  20:48
 * @Description: 文章分页查询对象
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndexArticlePageEntity {
    Integer pageNum;
    Integer pageSize;
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
     * 发布时间
     */
    private LocalDateTime createTime;

    /* 摘要 */
    private String summary;

    /**
     * 分类 ID
     */
    private Long categoryId;
    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 标签集合
     */
    private List<TagEntity> tags;

    /**
     * 是否置顶
     */
    private Boolean isTop;
}
