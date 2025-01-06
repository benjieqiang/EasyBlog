package com.ben.domain.web.adapter.repository;

import com.ben.domain.web.model.aggregate.WebWikiCatalogAggregate;
import com.ben.domain.web.model.aggregate.WikiPreNextArticleAggregate;
import com.ben.domain.web.model.entity.StatisticsInfoEntity;
import com.ben.domain.web.model.entity.WikiInfoEntity;

import java.util.List;

/**
 * @InterfaceName: IStatisticsRepository
 * @Description: 前端 wiki仓储层
 * @Author: benjieqiang
 * @LastChangeDate: 2025/1/4 12:02 PM
 * @Version: v1.0
 */

public interface IWikiRepository {
    List<WikiInfoEntity> findWikiList();

    WikiPreNextArticleAggregate findArticlePreNext(Long id, Long articleId);

    List<WebWikiCatalogAggregate> findWikiCatalogList(Long id);
}

