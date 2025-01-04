package com.ben.infrastructure.persistent.dao;

import com.ben.infrastructure.persistent.po.Article;
import com.ben.infrastructure.persistent.po.ArticleCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @InterfaceName: IArticleContentDao
 * @Description: 添加描述
 * @Author: benjieqiang
 * @LastChangeDate: 2024/12/14 8:55 PM
 * @Version: v1.0
 */
@Mapper
public interface IArticleDao {
    /* 插入 */
    void insert(Article article);
    /* 删除 */
    int deleteByArticleId(Long articleId);
    /* 根据文章id更新 */
    int update(Article article);

    /* 根据文章id查询 */
    Article selectByArticleId(Long articleId);

    /* 查询最大权重值记录 */
    Article selectMaxWeight();

    /* 分页查询 */
    List<Article> selectPageList(String title, LocalDateTime startDate, LocalDateTime endDate, Integer type);

    /* 根据文章 ID 批量分页查询 */
    List<Article> selectPageListByArticleIds(List<Long> articleIds);

    /* 查询上一篇文章 */
    Article selectPreArticle(Long id);

    /* 查询下一篇文章 */
    Article selectNextArticle(Long id);

    /* 阅读量+1 */
    int increaseReadNum(Long id);

    /* 查询所有记录的总阅读量 */
    Long selectAllReadNum();

    /* 批量更新文章 */
    int updateByIds(@Param("article") Article article, @Param("ids") List<Long> ids);

    Long selectCount();

    List<ArticleCount> selectDateArticlePublishCount(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}

