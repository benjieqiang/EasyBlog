package com.ben.test.infrastructure.repository;

import com.ben.domain.admin.model.entity.CategoryEntity;
import com.ben.domain.admin.model.entity.CategoryPageEntity;
import com.ben.domain.admin.model.entity.TagEntity;
import com.ben.domain.admin.model.entity.TagPageEntity;
import com.ben.infrastructure.persistent.dao.ITagDao;
import com.ben.infrastructure.persistent.repository.AdminTagRepository;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  10:11
 * @Description: AdminTagRepositoryTest
 * @Version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminTagRepositoryTest {
    @Autowired
    private AdminTagRepository repository;
    /* 添加分类标签 */
    @Test
    public void test_addTags() {
        List<String> tags = new ArrayList<>(Arrays.asList("test1","test2","test3"));
        repository.addTags(tags);
    }

    /* 删除标签 */
    @Test
    public void test_deleteTag() {
        int count = repository.deleteTag(40L);
        log.info("测试结果:{}", count);
    }

    /* 分页查询标签列表 */
    @Test
    public void test_findTagPageList() {
        LocalDateTime beginDate = LocalDateTime.of(2024, 1, 2, 0, 0, 0);;
        LocalDateTime endDate =LocalDateTime.of(2024, 12, 31, 0, 0, 0);;
        Integer pageNum = 1;
        Integer pageSize = 5;

        String name = "test";
        TagPageEntity tagPageEntity = TagPageEntity.builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .name(name)
                .startDate(beginDate)
                .endDate(endDate).build();
        PageInfo<TagEntity> tagPageEntityPageInfo = repository.findTagPageList(tagPageEntity);
        log.info("测试结果：{}", tagPageEntityPageInfo);
    }

    /* 根据tagName模糊查询 */
    @Test
    public void test_selectByName() {
        List<TagEntity> tagEntities = repository.selectByName("py");
        log.info("测试结果：{}", tagEntities);
    }

    /* 查询所有标签 */
    @Test
    public void test_findTagSelectList() {
        List<TagEntity> tagSelectList = repository.findTagSelectList();
        log.info("测试结果：{}", tagSelectList);
    }
}
