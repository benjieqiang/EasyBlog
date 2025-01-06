package com.ben.test.infrastructure.dao;

import com.ben.infrastructure.persistent.dao.IWikiDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-05  15:44
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class WikiDaoTest {
    @Autowired
    private IWikiDao wikiDao;

    @Test
    public void test_insert() {
        Long wikiId = 2L;
    }

}
