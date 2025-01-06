package com.ben.infrastructure.persistent.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-03  15:13
 * @Description: 用户持久化对象
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /* 自增id */
    private Long id;
    /* 用户名 */
    private String username;
    /* 密码 */
    private String password;
    /* 创建时间 */
    private LocalDateTime createTime;
    /* 更新时间 */
    private LocalDateTime updateTime;
    /* 0：未删除 1：已删除 */
    private Integer isDeleted;
}
