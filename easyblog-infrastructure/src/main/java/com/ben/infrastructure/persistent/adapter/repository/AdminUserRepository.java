package com.ben.infrastructure.persistent.adapter.repository;

import com.ben.domain.admin.repository.IAdminUserRepository;
import com.ben.infrastructure.persistent.dao.IUserDao;
import com.ben.infrastructure.persistent.dao.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-04  10:49
 * @Description: Admin 用户仓储服务
 * @Version: 1.0
 */
@Slf4j
@Component
public class AdminUserRepository implements IAdminUserRepository {
    @Resource
    private IUserDao userDao;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public int updatePassword(String username, String password) {
        // 加密密码
        String encodePassword = passwordEncoder.encode(password);
        return userDao.updatePasswordByUsername(User.builder().username(username).password(encodePassword).build());
    }

    @Override
    public String findUserInfo() {
        // 获取存储在 ThreadLocal 中的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 拿到用户名
        return authentication.getName();
    }
}
