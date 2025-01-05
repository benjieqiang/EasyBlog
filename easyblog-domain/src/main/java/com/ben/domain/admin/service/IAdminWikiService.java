package com.ben.domain.admin.service;

import com.ben.domain.admin.model.aggregate.WikiCatalogAggregate;
import com.ben.domain.admin.model.aggregate.WikiPageAggregate;
import com.ben.domain.admin.model.entity.WikiPageEntity;

import java.util.List;

/**
 * @InterfaceName: IAdminWikiService
 * @Description: 添加描述
 * @Author: benjieqiang
 * @LastChangeDate: 2025/1/5 9:56 AM
 * @Version: v1.0
 */

public interface IAdminWikiService {
    void addWiki(String title, String summary, String cover);

    Integer deleteWiki(Long id);

    void updateWikiIsTop(Long id, Boolean isTop);

    void updateWikiIsPublish(Long id, Boolean isPublish);

    void updateWiki(Long id, String title, String cover, String summary);

    WikiPageAggregate findWikiPageList(WikiPageEntity wikiPageEntity);

    List<WikiCatalogAggregate> findWikiCatalogList(Long id);

    void updateWikiCatalogs(Long wikiId, List<WikiCatalogAggregate> catalogAggregates);
}

