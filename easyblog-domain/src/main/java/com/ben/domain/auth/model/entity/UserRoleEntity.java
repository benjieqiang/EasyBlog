package com.ben.domain.auth.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-03  15:13
 * @Description: 用户角色实体对象
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleEntity {
    /* 用户名 */
    private String username;
    /* 角色 */
    private String role;
}
