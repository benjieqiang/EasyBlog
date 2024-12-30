package com.ben.infrastructure.persistent.dao;

import com.ben.infrastructure.persistent.po.Category;
import com.ben.infrastructure.persistent.po.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  08:46
 * @Description: 标签DAO
 * @Version: 1.0
 */
@Mapper
public interface ITagDao {
    List<Tag> selectByName(@Param("name") String name);

    void insert(Tag tag);

    void batchInsert(@Param("tags") List<String> tags);

    int updateTag(Long id);

    List<Tag> findTagList();

    List<Tag> selectPageList(@Param("name") String name,
                             @Param("startDate") LocalDateTime startDate,
                             @Param("endDate") LocalDateTime endDate);
}
