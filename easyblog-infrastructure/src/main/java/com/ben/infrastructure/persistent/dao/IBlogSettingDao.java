package com.ben.infrastructure.persistent.dao;

import com.ben.infrastructure.persistent.dao.po.BlogSetting;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  11:42
 * @Description: BlogSetting
 * @Version: 1.0
 */
@Mapper
public interface IBlogSettingDao {
    void insert(BlogSetting blogSetting);

    int update(BlogSetting blogSetting);

    BlogSetting selectBlogSetting(Long id);
}
