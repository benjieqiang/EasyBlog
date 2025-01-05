package com.ben.infrastructure.persistent.dao;

import com.ben.infrastructure.persistent.po.WikiCatalog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-05  10:14
 * @Description: IWikiCatalogDao
 * @Version: 1.0
 */
@Mapper
public interface IWikiCatalogDao {
    void insert(WikiCatalog catalog);

    void deleteByWikiId(Long wikiId);

    WikiCatalog selectFirstArticleId(Long wikiId);

    /* 查询某个知识库下所有目录 */
    List<WikiCatalog> selectByWikiId(Long wikiId);

    WikiCatalog selectPreArticle(Long wikiId, Long catalogId);

    WikiCatalog selectNextArticle(Long wikiId, Long catalogId);

    WikiCatalog selectByWikiIdAndArticleId(Long wikiId, Long articleId);

    void insertBatch(List<WikiCatalog> catalogs);
}
