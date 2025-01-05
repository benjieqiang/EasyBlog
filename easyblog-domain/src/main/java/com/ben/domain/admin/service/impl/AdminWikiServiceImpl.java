package com.ben.domain.admin.service.impl;

import com.ben.domain.admin.model.aggregate.WikiCatalogAggregate;
import com.ben.domain.admin.model.aggregate.WikiPageAggregate;
import com.ben.domain.admin.model.entity.WikiPageEntity;
import com.ben.domain.admin.repository.IAdminWikiRepository;
import com.ben.domain.admin.service.IAdminWikiService;
import com.ben.types.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-05  09:56
 * @Description: 后端-wiki管理
 * @Version: 1.0
 */
@Service
@Slf4j
public class AdminWikiServiceImpl implements IAdminWikiService {
    @Autowired
    private IAdminWikiRepository repository;

    @Override
    public void addWiki(String title, String summary, String cover) {
        repository.addWiki(title, summary, cover);
    }

    @Override
    public Integer deleteWiki(Long id) {
        return repository.deleteWiki(id);
    }

    @Override
    public void updateWikiIsTop(Long id, Boolean isTop) {
        repository.updateWikiIsTop(id, isTop);
    }

    @Override
    public void updateWikiIsPublish(Long id, Boolean isPublish) {
        repository.updateWikiIsPublish(id, isPublish);
    }

    @Override
    public void updateWiki(Long id, String title, String cover, String summary) {
        repository.updateWiki(id, title, cover, summary);
    }

    @Override
    public WikiPageAggregate findWikiPageList(WikiPageEntity wikiPageEntity) {
        return repository.findWikiPageList(wikiPageEntity);
    }

    @Override
    public List<WikiCatalogAggregate> findWikiCatalogList(Long id) {
        return repository.findWikiCatalogList(id);
    }

    @Override
    public void updateWikiCatalogs(Long wikiId, List<WikiCatalogAggregate> catalogAggregates) {
        repository.updateWikiCatalogs(wikiId, catalogAggregates);
    }
}
