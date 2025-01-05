package com.ben.infrastructure.persistent.dao;

import com.ben.infrastructure.persistent.po.Wiki;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-05  10:14
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper
public interface IWikiDao {
    void insert(Wiki wiki);

    int deleteByWikiId(Long wikiId);

    Wiki selectByWikiId(Long wikiId);

    List<Wiki> selectPublished();

    Wiki selectMaxWeight();

    List<Wiki> selectPageList(@Param("title") String title,
                              @Param("startDate") LocalDateTime startDate,
                              @Param("endDate") LocalDateTime endDate,
                              @Param("isPublish") Integer isPublish);

    int update(Wiki wiki);
}
