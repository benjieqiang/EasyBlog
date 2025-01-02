package com.ben.domain.admin.service.impl;

import com.ben.domain.admin.model.aggregate.ArticleDetailAggregate;
import com.ben.domain.admin.model.entity.ArticleEntity;
import com.ben.domain.admin.model.entity.ArticlePageEntity;
import com.ben.domain.admin.repository.IAdminArticleRepository;
import com.ben.domain.admin.service.IAdminArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-30  15:24
 * @Description: 文章管理
 * @Version: 1.0
 */
@Service
public class AdminArticleServiceImpl implements IAdminArticleService {

    @Autowired
    private IAdminArticleRepository articleRepository;

    @Override
    public void publishArticle(ArticleEntity articleEntity) {
        articleRepository.publishArticle(articleEntity);
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteArticleById(id);
    }

    @Override
    public PageInfo<ArticleEntity> findArticlePageList(ArticlePageEntity articlePageEntity) {
        return articleRepository.findArticlePageList(articlePageEntity);
    }

    @Override
    public ArticleDetailAggregate findArticleDetail(Long articleId) {
        return articleRepository.findArticleDetail(articleId);
    }
}
