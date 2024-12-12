package com.ben.domain.admin.repository;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-05  23:02
 * @Description: 用户仓储服务
 * @Version: 1.0
 */
public interface IAdminUserRepository {
    int updatePassword(String username, String password);

    String findUserInfo();
}
