package com.ben.domain.web.repository;

import com.ben.domain.web.model.aggregate.IndexArticleDetailAggregate;
import com.ben.domain.web.model.entity.IndexArticlePageEntity;
import com.github.pagehelper.PageInfo;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  08:54
 * @Description: 文章仓储
 * @Version: 1.0
 */
public interface IArticleRepository {

    /* 查询所有标签 */
    PageInfo<IndexArticlePageEntity> findArticlePageList(Integer current, Integer size);

    IndexArticleDetailAggregate findArticleDetail(Long articleId);
}
