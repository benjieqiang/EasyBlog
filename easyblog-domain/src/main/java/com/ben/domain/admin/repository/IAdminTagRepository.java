package com.ben.domain.admin.repository;

import com.ben.domain.admin.model.entity.TagEntity;
import com.ben.domain.admin.model.entity.TagPageEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  08:54
 * @Description: 分类仓储
 * @Version: 1.0
 */
public interface IAdminTagRepository {
    /* 添加分类标签 */
    void addTags(List<String> tags);

    /* 删除标签 */
    int deleteTag(Long id);

    /* 分页查询标签列表 */
    PageInfo<TagEntity> findTagPageList(TagPageEntity tagPageEntity);

    /* 根据tagName模糊查询 */
    List<TagEntity> selectByName(String tagName);

    /* 查询所有标签 */
    List<TagEntity> findTagSelectList();
}
