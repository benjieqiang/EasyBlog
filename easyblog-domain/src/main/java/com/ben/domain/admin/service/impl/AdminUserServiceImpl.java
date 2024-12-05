package com.ben.domain.admin.service.impl;

import com.ben.domain.admin.repository.IAdminUserRepository;
import com.ben.domain.admin.service.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-05  22:55
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class AdminUserServiceImpl implements IAdminUserService {

    @Autowired
    private IAdminUserRepository repository;

    @Override
    public int updatePassword(String username, String password) {
        return repository.updatePassword(username, password);
    }

    @Override
    public String findUserInfo() {
        return repository.findUserInfo();
    }
}
