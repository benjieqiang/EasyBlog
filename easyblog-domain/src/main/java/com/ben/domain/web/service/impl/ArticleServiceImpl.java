package com.ben.domain.web.service.impl;

import com.ben.domain.web.model.aggregate.WebArticleDetailAggregate;
import com.ben.domain.web.model.entity.IndexArticlePageEntity;
import com.ben.domain.web.adapter.repository.IArticleRepository;
import com.ben.domain.web.service.IArticleService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-02  18:30
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Slf4j
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private IArticleRepository articleRepository;

    @Override
    public PageInfo<IndexArticlePageEntity> findArticlePageList(Integer current, Integer size) {
        return articleRepository.findArticlePageList(current, size);
    }

    @Override
    public WebArticleDetailAggregate findArticleDetail(Long articleId) {
        return articleRepository.findArticleDetail(articleId);
    }
}
