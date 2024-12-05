package com.ben.infrastructure.persistent.repository;

import com.ben.domain.auth.model.entity.UserEntity;
import com.ben.domain.auth.model.entity.UserRoleEntity;
import com.ben.domain.auth.repository.IAuthRepository;
import com.ben.infrastructure.persistent.dao.IUserDao;
import com.ben.infrastructure.persistent.dao.IUserRoleDao;
import com.ben.infrastructure.persistent.po.User;
import com.ben.infrastructure.persistent.po.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-04  10:49
 * @Description: 登录仓储服务
 * @Version: 1.0
 */
@Slf4j
@Component
public class AuthRepository implements IAuthRepository {
    @Resource
    private IUserDao userDao;

    @Resource
    private IUserRoleDao userRoleDao;

    @Override
    public void insert(UserEntity userEntity) {
        User user = User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .isDeleted(userEntity.getIsDeleted())
                .build();
        userDao.insert(user);
    }

    @Override
    public int updatePasswordByUsername(String username) {
        return userDao.updatePasswordByUsername(username);
    }

    @Override
    public UserEntity queryUserByUsername(String username) {
        User user = userDao.queryByUsername(username);
        UserEntity userEntity = new UserEntity();
        if (null == user) return null;
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setIsDeleted(user.getIsDeleted());
        return userEntity;
    }

    @Override
    public void insert(UserRoleEntity userRoleEntity) {
        UserRole userRole = new UserRole();
        userRole.setUsername(userRoleEntity.getUsername());
        userRole.setRole(userRoleEntity.getRole());
        userRoleDao.insert(userRole);
    }

    @Override
    public int updateRoleByUsername(UserRoleEntity userRoleReq) {
        UserRole userRole = new UserRole();
        userRole.setUsername(userRoleReq.getUsername());
        userRole.setRole(userRoleReq.getRole());
        return userRoleDao.updateRoleByUsername(userRole);
    }

    @Override
    public List<UserRoleEntity> queryUserRoleByUsername(String username) {
        List<UserRole> userRoles = userRoleDao.queryByUsername(username);
        if (CollectionUtils.isEmpty(userRoles)) return null;
        List<UserRoleEntity> userRoleEntities = new ArrayList<>(userRoles.size());
        for (UserRole userRole : userRoles) {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUsername(userRole.getUsername());
            userRoleEntity.setRole(userRole.getRole());
            userRoleEntities.add(userRoleEntity);
        }
        return userRoleEntities;
    }
}
