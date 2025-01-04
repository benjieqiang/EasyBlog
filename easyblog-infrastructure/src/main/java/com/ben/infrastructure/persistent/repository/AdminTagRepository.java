package com.ben.infrastructure.persistent.repository;

import com.ben.domain.admin.model.entity.TagEntity;
import com.ben.domain.admin.model.entity.TagPageEntity;
import com.ben.domain.admin.repository.IAdminTagRepository;
import com.ben.infrastructure.persistent.dao.ITagDao;
import com.ben.infrastructure.persistent.po.Tag;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  09:03
 * @Description: Tag
 * @Version: 1.0
 */
@Slf4j
@Component
public class AdminTagRepository implements IAdminTagRepository {
    @Autowired
    private ITagDao tagDao;

    @Override
    public void addTags(List<String> tags) {
        tagDao.batchInsert(tags);
    }

    @Override
    public int deleteTag(Long id) {
        // todo: 如果category下面有文章，不能删除，直接抛出异常；
        return tagDao.deleteTag(id);
    }

    @Override
    public PageInfo<TagEntity> findTagPageList(TagPageEntity tagPageEntity) {
        Integer pageNum = tagPageEntity.getPageNum();
        Integer pageSize = tagPageEntity.getPageSize();
        String name = tagPageEntity.getName();
        LocalDateTime startDate = tagPageEntity.getStartDate();
        LocalDateTime endDate = tagPageEntity.getEndDate();
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);

        List<Tag> tags = tagDao.selectPageList(name, startDate, endDate);
        if (tags == null) return null;
        List<TagEntity> tagEntities = tags.stream().map(
                tag -> TagEntity.builder()
                        .id(tag.getId())
                        .name(tag.getName())
                        .createTime(tag.getCreateTime())
                        .updateTime(tag.getUpdateTime())
                        .articlesTotal(tag.getArticlesTotal())
                        .build()
        ).collect(Collectors.toList());

        return new PageInfo<>(tagEntities);
    }

    @Override
    public List<TagEntity> selectByName(String tagName) {
        List<Tag> tags = tagDao.selectByName(tagName);
        if (tags == null) return null;
        List<TagEntity> tagEntities = tags.stream().map(
                tag -> TagEntity.builder()
                        .id(tag.getId())
                        .name(tag.getName())
                        .createTime(tag.getCreateTime())
                        .updateTime(tag.getUpdateTime())
                        .articlesTotal(tag.getArticlesTotal())
                        .build()

        ).collect(Collectors.toList());
        return tagEntities;
    }

    @Override
    public List<TagEntity> findTagSelectList() {
        List<Tag> tagList = tagDao.selectAll();
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
