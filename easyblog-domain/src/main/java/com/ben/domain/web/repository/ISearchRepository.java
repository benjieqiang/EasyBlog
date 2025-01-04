package com.ben.domain.web.repository;

import com.ben.domain.web.model.aggregate.ArticleSearchAggregate;

/**
 * @InterfaceName: ISearchRepository
 * @Description: 添加描述
 * @Author: benjieqiang
 * @LastChangeDate: 2025/1/4 4:56 PM
 * @Version: v1.0
 */

public interface ISearchRepository {
    ArticleSearchAggregate searchArticlePageList(String word, int current, int size);
}

