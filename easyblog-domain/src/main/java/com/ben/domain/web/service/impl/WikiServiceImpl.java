package com.ben.domain.web.service.impl;

import com.ben.domain.web.model.aggregate.WebWikiCatalogAggregate;
import com.ben.domain.web.model.aggregate.WikiPreNextArticleAggregate;
import com.ben.domain.web.model.entity.WikiInfoEntity;
import com.ben.domain.web.adapter.repository.IWikiRepository;
import com.ben.domain.web.service.IWikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-05  19:27
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class WikiServiceImpl implements IWikiService {

    @Autowired
    private IWikiRepository repository;

    @Override
    public List<WikiInfoEntity> findWikiList() {
        return repository.findWikiList();
    }

    @Override
    public WikiPreNextArticleAggregate findArticlePreNext(Long id, Long articleId) {
        return repository.findArticlePreNext(id, articleId);
    }

    @Override
    public List<WebWikiCatalogAggregate> findWikiCatalogList(Long id) {
        return repository.findWikiCatalogList(id);
    }
}
