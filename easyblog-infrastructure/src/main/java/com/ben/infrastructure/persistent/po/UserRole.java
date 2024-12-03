package com.ben.infrastructure.persistent.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-03  15:13
 * @Description: 用户角色持久化对象
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    /* 自增id */
    private Long id;
    /* 用户名 */
    private String username;
    /* 角色 */
    private String role;
    /* 创建时间 */
    private Date createTime;
    /* 更新时间 */
    private Date updateTime;
}
