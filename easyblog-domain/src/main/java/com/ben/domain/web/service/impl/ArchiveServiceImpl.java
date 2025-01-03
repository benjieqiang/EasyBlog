package com.ben.domain.web.service.impl;

import com.ben.domain.web.model.aggregate.ArchiveArticleAggregate;
import com.ben.domain.web.repository.IArchiveRepository;
import com.ben.domain.web.service.IArchiveService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-03  11:32
 * @Description: 文章归类
 * @Version: 1.0
 */
@Service
@Slf4j
public class ArchiveServiceImpl implements IArchiveService {

    @Autowired
    private IArchiveRepository repository;

    @Override
    public PageInfo<ArchiveArticleAggregate> findArchivePageList(Integer current, Integer size) {
        return repository.findArchivePageList(current, size);
    }
}
