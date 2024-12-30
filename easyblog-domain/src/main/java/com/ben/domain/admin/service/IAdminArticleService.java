package com.ben.domain.admin.service;

import com.ben.domain.admin.model.entity.ArticleEntity;

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
}
