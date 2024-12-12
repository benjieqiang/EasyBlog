package com.ben.infrastructure.persistent.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-12  10:06
 * @Description: 文章分类Po
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    /* 分类id */
    private Long id;
    /* 分类名称 */
    private String name;
    /* 创建时间 */
    private Date createTime;
    /* 最后一次更新时间 */
    private Date updateTime;
    /* 逻辑删除标志位：0：未删除 1：已删除 */
    private Integer isDeleted;
    /* 文章总数 */
    private Integer articlesTotal;
}
