package com.ben.infrastructure.persistent.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-05  10:15
 * @Description: Wiki
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wiki {
    private Long id;

    private String title;

    private String cover;

    private String summary;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
    /* 0：未删除 1：已删除 */
    private Integer isDeleted;

    private Integer weight;
    /* 是否发布：0：未发布 1：已发布 */
    private Integer isPublish;
}
