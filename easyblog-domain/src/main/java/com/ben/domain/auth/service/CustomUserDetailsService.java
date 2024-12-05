package com.ben.domain.auth.service;

import com.ben.domain.auth.model.entity.UserEntity;
import com.ben.domain.auth.model.entity.UserRoleEntity;
import com.ben.domain.auth.repository.IAuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-04  20:19
 * @Description: 实现UserDetailsService的抽象方法并返回一个UserDetails对象，
 * 认证过程中SpringSecurity会调用这个方法访问数据库进行对用户的搜索
 * @Version: 1.0
 */
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Resource
    private IAuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中查询
        UserEntity userEntity = authRepository.queryUserByUsername(username);

        // 判断用户是否存在
        if (Objects.isNull(userEntity)) {
            throw new UsernameNotFoundException("该用户不存在");
        }

        // 用户角色
        List<UserRoleEntity> roleEntities = authRepository.queryUserRoleByUsername(username);

        String[] roleArr = null;

        if (!CollectionUtils.isEmpty(roleEntities)) {
            List<String> roles = roleEntities.stream().map(p -> p.getRole()).collect(Collectors.toList());
            roleArr = roles.toArray(new String[roles.size()]);
        }

        // authorities 用于指定角色
        return User.withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(roleArr)
                .build();
    }
}