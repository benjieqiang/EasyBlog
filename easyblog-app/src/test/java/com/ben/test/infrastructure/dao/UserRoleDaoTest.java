package com.ben.test.infrastructure.dao;

import com.ben.infrastructure.persistent.dao.IUserRoleDao;
import com.ben.infrastructure.persistent.dao.po.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-03  16:12
 * @Description: UserRoleDaoTest
 * @Version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleDaoTest {

    @Resource
    private IUserRoleDao userRoleDao;

    @Test
    public void test_insert() {
        UserRole userRole = UserRole.builder()
                .username("ben")
                .role("ROLE_ADMIN")
                .build();
        userRoleDao.insert(userRole);
    }


    /*
     test用户只有一个；
     */
    @Test
    public void test_updateRoleByUsername() {
        UserRole userRole = new UserRole();
        userRole.setUsername("test");
        userRole.setRole("ROLE_TEST");
        int result = userRoleDao.updateRoleByUsername(userRole);
        log.info("测试结果：{}", result);
    }

    @Test
    public void test_queryByUsername() {
        List<UserRole> userRoleList = userRoleDao.queryByUsername("ben");
        log.info("测试结果：{}", userRoleList);
    }
}