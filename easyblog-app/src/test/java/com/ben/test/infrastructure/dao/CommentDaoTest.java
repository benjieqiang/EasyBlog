package com.ben.test.infrastructure.dao;

import com.ben.infrastructure.persistent.dao.ICommentDao;
import com.ben.infrastructure.persistent.dao.po.Comment;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-06  09:53
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest

public class CommentDaoTest {
    @Autowired
    private ICommentDao commentDao;

    @Test
    public void test_update() {
        commentDao.update(Comment.builder()
                .id(1L)
                .reason("审核不通过，敏感词")
                .status(3)
                .build());
    }
}
