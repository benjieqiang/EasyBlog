package com.ben.infrastructure.persistent.dao;

import com.ben.infrastructure.persistent.po.ArticleContent;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName: IArticleContentDao
 * @Description: 文章正文Dao
 * @Author: benjieqiang
 * @LastChangeDate: 2024/12/14 8:55 PM
 * @Version: v1.0
 */
@Mapper
public interface IArticleContentDao {
    /* 插入 */
    void insert(ArticleContent articleContent);

    /* 根据文章 ID 删除记录 */
    int deleteByArticleId(Long articleId);

    /* 根据文章 ID 查询 */
    ArticleContent selectByArticleId(Long articleId);

    /* 更新  */
    int update(ArticleContent articleContent);
}

