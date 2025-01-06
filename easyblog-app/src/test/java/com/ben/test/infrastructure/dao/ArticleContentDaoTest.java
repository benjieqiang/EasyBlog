package com.ben.test.infrastructure.dao;

import com.ben.infrastructure.persistent.dao.IArticleContentDao;
import com.ben.infrastructure.persistent.dao.po.ArticleContent;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-30  11:21
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleContentDaoTest {
    @Autowired
    private IArticleContentDao articleContentDao;

    /* 插入 */
    @Test
    public void test_insert() {
        ArticleContent articleContent = new ArticleContent();
        articleContent.setArticleId(23L);
        articleContent.setContent("测试哈哈哈");
        articleContentDao.insert(articleContent);
    }

    /* 根据文章 ID 删除记录 */
    @Test
    public void test_deleteByArticleId() {
        int count = articleContentDao.deleteByArticleId(20L);
        log.info("测试结果{}", count);
    }

    /* 根据文章 ID 查询 */
    @Test
    public void test_selectByArticleId() {
        ArticleContent articleContent = articleContentDao.selectByArticleId(21L);
        log.info("测试结果{}", articleContent);
    }

    /* 通过文章 ID 更新  */
    @Test
    public void test_update() {
        ArticleContent articleContent = new ArticleContent();
        articleContent.setArticleId(23L);
        articleContent.setContent("测试哈哈哈更新哈哈");
        int count = articleContentDao.update(articleContent);
        log.info("测试结果{}", count);
    }
}
