package com.ben.domain.admin.repository;

import com.ben.domain.admin.model.aggregate.ArticleDetailAggregate;
import com.ben.domain.admin.model.entity.ArticleEntity;
import com.ben.domain.admin.model.entity.ArticlePageEntity;
import com.github.pagehelper.PageInfo;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-30  15:57
 * @Description: 文章仓储
 * @Version: 1.0
 */
public interface IAdminArticleRepository {
    void publishArticle(ArticleEntity articleEntity);

    void deleteArticleById(Long id);

    PageInfo<ArticleEntity> findArticlePageList(ArticlePageEntity articlePageEntity);

    ArticleDetailAggregate findArticleDetail(Long articleId);
}
