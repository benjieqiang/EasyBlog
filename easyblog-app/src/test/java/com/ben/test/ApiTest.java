package com.ben.test;

import com.ben.domain.auth.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private JwtUtil jwtutil;
    @Test
    public void test() {
        log.info("测试完成");
    }

    /**
     * @param :
     * @return void
     * @description 生成jwt key
     * FUymUYPFOHwnXsf1zSNyb3MJExs6Wdj09FYsZkOTNjWtdZYY245ONXBGYWerMUKlJp7zVXpB
     * @author benjieqiang
     * @date 2024/12/4 8:10 PM
     */
    @Test
    public void test_generateBase64Key() {
        String key = jwtutil.generateBase64Key();
        log.info("key: " + key);
    }

    /**
     * @param :
     * @return void
     * @description 加密测试，admin,密码insert到user,user_role新建admin为管理员
     * $2a$10$TWNYR0ztTCSCs2SD4KZ0yu6QcXM0nAdMQrE7xvuZfj5OjYJ81vAO6
     * @author benjieqiang
     * @date 2024/12/4 8:17 PM
     */
    @Test
    public void test_passwordEncode() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        log.info("encode: {}", encoder.encode("test123"));
    }
}
