package com.ben.infrastructure.persistent.dao;

import com.ben.infrastructure.persistent.dao.po.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * @InterfaceName: ICommentDao
 * @Description: 评论Dao
 * @Author: benjieqiang
 * @LastChangeDate: 2025/1/6 9:26 AM
 * @Version: v1.0
 */
@Mapper
public interface ICommentDao {
    void insert(Comment comment);

    int deleteById(Long id);

    int deleteByParentCommentId(Long parentCommentId);

    Comment selectById(Long id);

    List<Comment> selectByRouterUrlAndStatus(String routerUrl, Integer status);

    List<Comment> selectByReplyCommentId(Long replyCommentId);

    List<Comment> selectPageList(@Param("routerUrl") String routerUrl,
                                 @Param("startDate") LocalDate startDate,
                                 @Param("endDate") LocalDate endDate,
                                 @Param("status") Integer status);

    int update(Comment comment);
}

