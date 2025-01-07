package com.ben.infrastructure.persistent.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  08:46
 * @Description: 标签po
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    /* 标签id */
    private Long id;
    /* 标签名称 */
    private String name;
    /* 创建时间 */
    private LocalDateTime createTime;
    /* 最后一次更新时间 */
    private LocalDateTime updateTime;
    /* 0：未删除 1：已删除 */
    private Integer isDeleted;
    /* 文章总数 */
    private Integer articlesTotal;

}