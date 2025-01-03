package com.ben.test.infrastructure.dao;

import com.ben.infrastructure.persistent.dao.IArticleDao;
import com.ben.infrastructure.persistent.po.Article;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
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
public class ArticleDaoTest {
    @Autowired
    private IArticleDao articleDao;

    /* 插入 */
    @Test
    public void test_insert() {
        Article article = Article.builder()
                .title("测试")
                .cover("https://23423423.png")
                .summary("测试哈哈")
                .weight(22)
                .build();
        articleDao.insert(article);
        log.info("测试结果: {}, {}", article.getId());
    }

    /* 删除 */
    @Test
    public void test_deleteByArticleId() {
        Long deleteId = 22L;
        int count = articleDao.deleteByArticleId(deleteId);
        log.info("测试结果: {}", count);
    }
    /* 更新 */
    @Test
    public void test_updateByArticleId() {
        Article article = Article.builder()
                .id(33L)
                .title("测试更新")
                .cover("https://23423423.png")
                .summary("测试更新哈哈")
                .build();
        int count = articleDao.update(article);
        log.info("测试结果: {}", count);
    }


    /* 查询最大权重值记录 */
    @Test
    public void test_selectMaxWeight() {
        Article article = articleDao.selectMaxWeight();
        log.info("测试结果: {}", article);
    }

    /* 分页查询 */
    @Test
    public void test_selectPageList() {
        LocalDateTime beginDate = LocalDateTime.of(2024, 1, 2, 0, 0, 0);
        ;
        LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0, 0);
        ;
        String title = "测试";

        List<Article> articleList = articleDao.selectPageList(title, beginDate, null, null);
        log.info("测试结果：{}", articleList);
    }

    /* 分页查询 */
    @Test
    public void test_selectPageList2() {
        // 开启分页
        PageHelper.startPage(1, 2);
        List<Article> articleList = articleDao.selectPageList(null, null, null, null);
        log.info("测试结果：{}", new PageInfo<>(articleList));
    }

    /* 根据文章 ID 批量分页查询 */
    @Test
    public void test_selectPageListByArticleIds() {
        List<Long> articleIds = Arrays.asList(20L, 21L);

        List<Article> articleList = articleDao.selectPageListByArticleIds(articleIds);
        log.info("测试结果：{}", articleList);
    }

    /* 查询上一篇文章 */
    @Test
    public void test_selectPreArticle() {
        Long articleId = 20L;
        Article article = articleDao.selectPreArticle(articleId);
        log.info("测试结果: {}", article);
    }

    /* 查询下一篇文章 */
    @Test
    public void test_selectNextArticle() {
        Long articleId = 13L;
        Article article = articleDao.selectNextArticle(articleId);
        log.info("测试结果: {}", article);
    }

    /* 阅读量+1 */
    @Test
    public void test_increaseReadNum() {
        int count = articleDao.increaseReadNum(22L);
        log.info("测试结果: {}", count);
    }

    /* 查询所有记录的总阅读量 */
    @Test
    public void test_selectAllReadNum() {
        Long readNum = articleDao.selectAllReadNum();
        log.info("测试结果: {}", readNum);
    }

    /* 批量更新文章 */
    @Test
    public void test_updateByIds() {
        Article article = Article.builder()
                .title("测试批量更新哈哈")
                .cover("https://23423423.png")
                .summary("测试批量更新哈哈")
                .type(2)
                .weight(40)
                .build();
        List<Long> articleList = Arrays.asList(21L, 22L);
        int count = articleDao.updateByIds(article, articleList);
        log.info("测试结果: {}", count);
    }
}
