package com.ben.domain.admin.service;

/**
 * @InterfaceName: IAdminUserService
 * @Description: 用户管理
 * @Author: benjieqiang
 * @LastChangeDate: 2024/12/5 10:50 PM
 * @Version: v1.0
 */
public interface IAdminUserService {

    int updatePassword(String username, String password);

    String findUserInfo();
}

