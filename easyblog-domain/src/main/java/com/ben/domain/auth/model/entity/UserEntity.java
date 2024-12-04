package com.ben.domain.auth.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-03  15:13
 * @Description: 用户实体对象
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    /* 用户名 */
    private String username;
    /* 密码 */
    private String password;
    /* 0：未删除 1：已删除 */
    private Integer isDeleted;
}
