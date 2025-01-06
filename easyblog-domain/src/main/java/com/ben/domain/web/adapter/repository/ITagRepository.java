package com.ben.domain.web.adapter.repository;

import com.ben.domain.web.model.entity.TagArticlePageEntity;
import com.ben.domain.web.model.entity.TagEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  08:54
 * @Description: 分类仓储
 * @Version: 1.0
 */
public interface ITagRepository {

    /* 查询所有标签 */
    List<TagEntity> findTagSelectList(Long size);

    PageInfo<TagArticlePageEntity> findTagArticlePageList(Long tagId, Integer current, Integer size);
}
