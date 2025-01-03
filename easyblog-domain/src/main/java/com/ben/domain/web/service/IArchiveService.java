package com.ben.domain.web.service;

import com.ben.domain.web.model.aggregate.ArchiveArticleAggregate;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @InterfaceName: IArchiveService
 * @Description: 归类service
 * @Author: benjieqiang
 * @LastChangeDate: 2025/1/3 11:31 AM
 * @Version: v1.0
 */

public interface IArchiveService {
    PageInfo<ArchiveArticleAggregate> findArchivePageList(Integer current, Integer size);
}

