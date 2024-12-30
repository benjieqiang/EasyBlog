package com.ben.test.infrastructure.dao

;

import com.ben.infrastructure.persistent.dao.IArticleTagRelDao;
import com.ben.infrastructure.persistent.po.ArticleTagRel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-30  11:21
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTagRelDaoTest {
    @Autowired
    private IArticleTagRelDao articleTagRelDao;
    /* 插入 */
    @Test
    public void test_insert() {
        ArticleTagRel articleTagRel = new ArticleTagRel();
        articleTagRel.setArticleId(1L);
        articleTagRel.setTagId(20L);
        articleTagRelDao.insert(articleTagRel);

        ArticleTagRel articleTagRel2 = new ArticleTagRel();
        articleTagRel2.setArticleId(30L);
        articleTagRel2.setTagId(20L);
        articleTagRelDao.insert(articleTagRel2);
    }

    /* 根据文章 ID 删除关联记录 */
    @Test
    public void test_deleteByArticleId() {
        int count = articleTagRelDao.deleteByArticleId(30L);
        log.info("测试结果：{}", count);
    }

    /* 根据文章 ID 来查询 */
    @Test
    public void test_selectByArticleId() {
        List<ArticleTagRel> articleTagRels = articleTagRelDao.selectByArticleId(20L);
        log.info("测试结果：{}", articleTagRels);
    }

    /* 根据标签 ID 查询 */
    @Test
    public void test_selectOneByTagId() {
        ArticleTagRel articleTagRel = articleTagRelDao.selectOneByTagId(2L);
        log.info("测试结果：{}", articleTagRel);
    }

    /* 根据文章 ID 集合批量查询 */
    @Test
    public void test_selectByArticleIds() {
        List<ArticleTagRel> articleTagRels = articleTagRelDao.selectByArticleIds(Arrays.asList(1L, 20L));
        log.info("测试结果：{}", articleTagRels);
    }

    /* 查询该标签 ID 下所有关联记录 */
    @Test
    public void test_selectByTagId() {
        List<ArticleTagRel> articleTagRels = articleTagRelDao.selectByTagId(20L);
        log.info("测试结果：{}", articleTagRels);
    }
}
