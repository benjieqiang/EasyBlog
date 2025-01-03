package com.ben.trigger.http.dto.article;

import com.ben.trigger.http.dto.tag.FindIndexTagRspDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindIndexArticleDetailRspDTO {
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
    private List<FindIndexTagRspDTO> tags;
    /**
     * 上一篇文章
     */
    private FindIndexPreNextArticleRspDTO preArticle;
    /**
     * 下一篇文章
     */
    private FindIndexPreNextArticleRspDTO nextArticle;
    /**
     * 总字数
     */
    private Integer totalWords;
    /**
     * 阅读时长
     */
    private String readTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;
}

