package com.ben.domain.admin.model.aggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-14  20:41
 * @Description: 文章详情聚合
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailAggregate {
    /* 文章id */
    private Long articleId;
    /* 文章标题 */
    private String title;
    /* 文章封面 */
    private String cover;
    /* 文章摘要 */
    private String summary;
    /* 文章正文 */
    private String content;
    /* 文章类型 */
    private Integer type;
    /* 分类id */
    private Long categoryId;
    /* 标签列表 */
    private List<Long> tagIds;
}
