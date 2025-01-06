package com.ben.infrastructure.persistent.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-14  20:41
 * @Description: 文章
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    /* 文章id */
    private Long id;
    /* 文章标题 */
    private String title;
    /* 文章封面 */
    private String cover;
    /* 文章摘要 */
    private String summary;
    /* 创建时间 */
    private LocalDateTime createTime;
    /* 最后一次更新时间 */
    private LocalDateTime updateTime;
    /* 删除标志位：0：未删除 1：已删除 */
    private Boolean isDeleted;
    /* 被阅读次数 */
    private Long readNum;
    /* 文章权重，用于是否置顶（0: 未置顶；>0: 参与置顶，权重值越高越靠前） */
    private Integer weight;
    /* 文章类型 - 1：普通文章，2：收录于知识库 */
    private Integer type;
}
