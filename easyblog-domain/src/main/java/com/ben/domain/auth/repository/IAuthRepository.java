package com.ben.domain.auth.repository;

import com.ben.domain.auth.model.entity.UserEntity;
import com.ben.domain.auth.model.entity.UserRoleEntity;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-03  17:47
 * @Description: 登录仓储服务
 * @Version: 1.0
 */
public interface IAuthRepository {
    void insert(UserEntity userEntity);

    int updatePasswordByUsername(String username);

    UserEntity queryUserByUsername(String username);

    void insert(UserRoleEntity userRoleEntity);

    int updateRoleByUsername(UserRoleEntity userRoleReq);

    List<UserRoleEntity> queryUserRoleByUsername(String username);

}
