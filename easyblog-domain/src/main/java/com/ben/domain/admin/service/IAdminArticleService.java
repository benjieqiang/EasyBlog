package com.ben.domain.admin.service;

import com.ben.domain.admin.model.entity.ArticleEntity;
import com.ben.domain.admin.model.entity.ArticlePageEntity;
import com.github.pagehelper.PageInfo;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-30  15:24
 * @Description: 文章管理
 * @Version: 1.0
 */
public interface IAdminArticleService {

    /* 发布文章 */
    void publishArticle(ArticleEntity articleEntity);

    void deleteArticle(Long id);

    PageInfo<ArticleEntity> findArticlePageList(ArticlePageEntity articlePageEntity);

    ArticleEntity findArticleDetail(Long articleId);
}
