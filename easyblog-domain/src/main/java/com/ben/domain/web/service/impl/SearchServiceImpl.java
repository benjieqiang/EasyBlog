package com.ben.domain.web.service.impl;

import com.ben.domain.web.model.aggregate.ArticleSearchAggregate;
import com.ben.domain.web.repository.ISearchRepository;
import com.ben.domain.web.service.ISearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-04  16:55
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Slf4j
public class SearchServiceImpl implements ISearchService {
    @Autowired
    private ISearchRepository repository;

    @Override
    public ArticleSearchAggregate searchArticlePageList(String word, int current, int size) {
        return repository.searchArticlePageList(word, current, size);
    }
}
