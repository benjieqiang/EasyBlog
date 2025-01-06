package com.ben.domain.web.adapter.repository;

import com.ben.domain.web.model.aggregate.ArchiveArticleAggregate;
import com.github.pagehelper.PageInfo;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-03  11:40
 * @Description: TODO
 * @Version: 1.0
 */
public interface IArchiveRepository {
    PageInfo<ArchiveArticleAggregate> findArchivePageList(Integer current, Integer size);
}
