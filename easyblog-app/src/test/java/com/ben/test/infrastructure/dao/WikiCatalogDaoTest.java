package com.ben.test.infrastructure.dao;

import com.ben.domain.admin.model.valobj.WikiCatalogLevelVO;
import com.ben.infrastructure.persistent.dao.IWikiCatalogDao;
import com.ben.infrastructure.persistent.po.WikiCatalog;
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
public class WikiCatalogDaoTest {
    @Autowired
    private IWikiCatalogDao wikiCatalogDao;

    @Test
    public void test_insert() {
        Long wikiId = 2L;
        wikiCatalogDao.insert(WikiCatalog.builder().wikiId(wikiId).level(WikiCatalogLevelVO.ONE.getValue()).title("概述").sort(1).build());
        wikiCatalogDao.insert(WikiCatalog.builder().wikiId(wikiId).level(WikiCatalogLevelVO.ONE.getValue()).title("基础").sort(2).build());
    }

    @Test
    public void test_selectFirstArticleId() {
        WikiCatalog wikiCatalog = wikiCatalogDao.selectFirstArticleId(9L);
        log.info("测试结果： {}", wikiCatalog);
    }

}
