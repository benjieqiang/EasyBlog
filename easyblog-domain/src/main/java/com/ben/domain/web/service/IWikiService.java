package com.ben.domain.web.service;

import com.ben.domain.web.model.aggregate.WebWikiCatalogAggregate;
import com.ben.domain.web.model.aggregate.WikiPreNextArticleAggregate;
import com.ben.domain.web.model.entity.WikiInfoEntity;

import java.util.List;

/**
 * @InterfaceName: IWikiService
 * @Description: 添加描述
 * @Author: benjieqiang
 * @LastChangeDate: 2025/1/5 7:16 PM
 * @Version: v1.0
 */

public interface IWikiService {
    List<WikiInfoEntity> findWikiList();

    WikiPreNextArticleAggregate findArticlePreNext(Long id, Long articleId);

    List<WebWikiCatalogAggregate> findWikiCatalogList(Long id);
}

