package com.ben.domain.web.model.aggregate;

import com.ben.domain.web.model.entity.ArticleEntity;
import com.ben.domain.web.model.entity.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-02  18:26
 * @Description: 文章详情聚合
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndexArticleDetailAggregate {
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章正文（HTML）
     */
    private String content;
    /**
     * 发布时间
     */
    private LocalDateTime createTime;
    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 分类 ID
     */
    private Long categoryId;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 阅读量
     */
    private Long readNum;
    /**
     * 标签集合
     */
    private List<TagEntity> tags;
    /**
     * 上一篇文章
     */
    private ArticleEntity preArticle;
    /**
     * 下一篇文章
     */
    private ArticleEntity nextArticle;
    /**
     * 总字数
     */
    private Integer totalWords;
    /**
     * 阅读时长
     */
    private String readTime;
}
