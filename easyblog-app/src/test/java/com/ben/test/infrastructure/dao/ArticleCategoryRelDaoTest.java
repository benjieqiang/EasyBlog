package com.ben.test.infrastructure.dao;

import com.ben.infrastructure.persistent.dao.IArticleCategoryRelDao;
import com.ben.infrastructure.persistent.po.ArticleCategoryRel;
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
public class ArticleCategoryRelDaoTest {

    @Autowired
    private IArticleCategoryRelDao articleCategoryRelDao;
    /* 插入 */
    @Test
    public void test_insert() {
        ArticleCategoryRel articleCategoryRel = new ArticleCategoryRel();
        articleCategoryRel.setArticleId(23L);
        articleCategoryRel.setCategoryId(16L);
        articleCategoryRelDao.insert(articleCategoryRel);
    }

    /* 根据文章 ID 删除关联记录 这里直接删除了*/
    @Test
    public void test_deleteByArticleId() {
        int count = articleCategoryRelDao.deleteByArticleId(23L);
        log.info("测试结果{}", count);
    }

    /* 根据文章 ID 来查询 */
    @Test
    public void test_selectByArticleId() {
        ArticleCategoryRel articleCategoryRel = articleCategoryRelDao.selectByArticleId(29L);
        log.info("测试结果{}", articleCategoryRel);
    }

    /* 根据分类 ID 查询 */
    @Test
    public void test_selectOneByCategoryId() {
        List<ArticleCategoryRel> articleCategoryRels = articleCategoryRelDao.selectListByCategoryId(9L);
        log.info("测试结果{}", articleCategoryRels);
    }

    /* 根据文章 ID 集合批量查询 */
    @Test
    public void test_selectByArticleIds() {
        List<ArticleCategoryRel> articleCategoryRels = articleCategoryRelDao.selectByArticleIds(Arrays.asList(13L, 14L, 15L));
        log.info("测试结果{}", articleCategoryRels);
    }

    /* 查询该分类 ID  下所有关联记录 */
    @Test
    public void test_selectListByCategoryId() {
        List<ArticleCategoryRel> articleCategoryRels = articleCategoryRelDao.selectListByCategoryId(9L);
        log.info("测试结果{}", articleCategoryRels);

    }
}
