package com.ben.infrastructure.persistent.repository;

import com.ben.domain.web.model.entity.TagEntity;
import com.ben.domain.web.repository.ITagRepository;
import com.ben.infrastructure.persistent.dao.ITagDao;
import com.ben.infrastructure.persistent.po.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  09:03
 * @Description: Tag
 * @Version: 1.0
 */
@Slf4j
@Component
public class WebTagRepository implements ITagRepository {
    @Autowired
    private ITagDao tagDao;

    @Override
    public List<TagEntity> findTagSelectList(Long size) {
        List<Tag> tagList = null;
        // 如果接口入参中未指定 size
        if (Objects.isNull(size) || size == 0) {
            // 查询所有分类
            tagList = tagDao.findTagList();
        } else {
            // 否则查询指定的数量
            tagList = tagDao.selectByLimit(size);
        }
        if (tagList == null) return null;
        return tagList.stream().map(
                tag -> TagEntity.builder()
                        .id(tag.getId())
                        .name(tag.getName())
                        .createTime(tag.getCreateTime())
                        .updateTime(tag.getUpdateTime())
                        .articlesTotal(tag.getArticlesTotal())
                        .build()
        ).collect(Collectors.toList());
    }
}
