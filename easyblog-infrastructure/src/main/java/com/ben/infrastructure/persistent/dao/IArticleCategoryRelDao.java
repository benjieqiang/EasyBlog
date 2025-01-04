package com.ben.infrastructure.persistent.dao;

import com.ben.infrastructure.persistent.po.ArticleCategoryRel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName: IArticleContentDao
 * @Description: 添加描述
 * @Author: benjieqiang
 * @LastChangeDate: 2024/12/14 8:55 PM
 * @Version: v1.0
 */
@Mapper
public interface IArticleCategoryRelDao {
    /* 插入 */
    void insert(ArticleCategoryRel articleCategoryRel);
    /* 根据文章 ID 删除关联记录 这里直接删除了*/
    int deleteByArticleId(Long articleId);
    /* 根据文章 ID 来查询 */
    ArticleCategoryRel selectByArticleId(Long articleId);
    /* 根据分类 ID 查询 */
    ArticleCategoryRel selectOneByCategoryId(Long categoryId);
    /* 查询所有 */
    /* 根据文章 ID 集合批量查询 */
    List<ArticleCategoryRel> selectByArticleIds(List<Long> articleIds);
    /* 查询该分类 ID  下所有关联记录 */
    List<ArticleCategoryRel> selectListByCategoryId(Long categoryId);
    /* 查询所有 */
    List<ArticleCategoryRel> selectAll();
}

