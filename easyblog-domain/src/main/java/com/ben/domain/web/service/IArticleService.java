package com.ben.domain.web.service;

import com.ben.domain.web.model.aggregate.IndexArticleDetailAggregate;
import com.ben.domain.web.model.entity.IndexArticlePageEntity;
import com.github.pagehelper.PageInfo;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-30  15:24
 * @Description: 文章管理
 * @Version: 1.0
 */
public interface IArticleService {

    IndexArticleDetailAggregate findArticleDetail(Long articleId);

    PageInfo<IndexArticlePageEntity> findArticlePageList(Integer current, Integer size);
}
