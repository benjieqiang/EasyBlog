package com.ben.test.infrastructure.dao;

import com.ben.infrastructure.persistent.dao.ICategoryDao;
import com.ben.infrastructure.persistent.po.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-03  16:11
 * @Description: CategoryDaoTest
 * @Version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryDaoTest {

    @Resource
    private ICategoryDao categoryDao;

    @Test
    public void test_selectByCategoryId() {
        Category category = categoryDao.selectByCategoryId(20L);
        log.info("测试结果：{}", category);
    }

    @Test
    public void test_selectByLimit() {
        List<Category> categories = categoryDao.selectByLimit(3L);
        log.info("测试结果：{}", categories);
    }

}
