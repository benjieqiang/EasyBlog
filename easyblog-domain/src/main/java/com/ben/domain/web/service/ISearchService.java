package com.ben.domain.web.service;

import com.ben.domain.web.model.aggregate.ArticleSearchAggregate;

/**
 * @InterfaceName: ISearchService
 * @Description: 添加描述
 * @Author: benjieqiang
 * @LastChangeDate: 2025/1/4 4:43 PM
 * @Version: v1.0
 */

public interface ISearchService {
    ArticleSearchAggregate searchArticlePageList(String word, int current, int size);
}

