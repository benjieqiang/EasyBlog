package com.ben.test.infrastructure;

import com.ben.infrastructure.persistent.dao.IUserDao;
import com.ben.infrastructure.persistent.po.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-03  16:11
 * @Description: UserDaoTest
 * @Version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Resource
    private IUserDao userDao;

    @Test
    public void test_insert() {
        User user = User.builder()
                .username("ben")
                .password("ben123")
                .build();
        userDao.insert(user);
    }

    @Test
    public void test_updatePasswordByUsername() {
        int result = userDao.updatePasswordByUsername(User.builder().username("ben").password("123456dd").build());
        log.info("测试结果：{}", result);
    }

    @Test
    public void test_queryByUsername() {
        User user = userDao.queryByUsername("ben");
        log.info("测试结果：{}", user);
    }
}
