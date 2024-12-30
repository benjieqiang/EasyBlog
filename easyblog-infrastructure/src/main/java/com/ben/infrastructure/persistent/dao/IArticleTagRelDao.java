package com.ben.infrastructure.persistent.dao;

import com.ben.infrastructure.persistent.po.ArticleTagRel;
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
public interface IArticleTagRelDao {
    /* 插入 */
    void insert(ArticleTagRel articleTagRel);
    /* 根据文章 ID 删除关联记录 */
    int deleteByArticleId(Long articleId);
    /* 根据文章 ID 来查询 */
    List<ArticleTagRel> selectByArticleId(Long articleId);
    /* 根据标签 ID 查询 */
    ArticleTagRel selectOneByTagId(Long tagId);
    /* 根据文章 ID 集合批量查询 */
    List<ArticleTagRel> selectByArticleIds(List<Long> articleIds);
    /* 查询该标签 ID 下所有关联记录 */
    List<ArticleTagRel> selectByTagId(Long tagId);
}

