package com.ben.infrastructure.persistent.dao;

import com.ben.infrastructure.persistent.dao.po.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @InterfaceName: ICategoryDao
 * @Description: 文章分类Dao
 * @Author: benjieqiang
 * @LastChangeDate: 2024/12/12 10:05 AM
 * @Version: v1.0
 */
@Mapper
public interface ICategoryDao {
    Category selectByName(String name);

    Category selectByCategoryId(Long id);

    List<Category> selectByCategoryIds(List<Long> ids);

    void insert(String name);

    int deleteCategory(Long id);

    List<Category> findCategoryList();

    List<Category> selectPageList(@Param("name") String name,
                                  @Param("startDate") LocalDateTime startDate,
                                  @Param("endDate") LocalDateTime endDate);

    List<Category> selectByLimit(Long size);

    List<Category> selectALl();

    Long selectCount();

    int update(Category category);
}

