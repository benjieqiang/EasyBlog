package com.ben.domain.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-12  10:29
 * @Description: 文章分类实体
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {
    /* 分类id */
    private Long id;
    /* 分类名称 */
    private String name;
    /* 创建时间 */
    private LocalDateTime createTime;
    /* 最后一次更新时间 */
    private LocalDateTime updateTime;
    /* 逻辑删除标志位：0：未删除 1：已删除 */
    private Integer isDeleted;
    /* 文章总数 */
    private Integer articlesTotal;
}
