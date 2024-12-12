package com.ben.test.infrastructure.repository;

import com.ben.domain.admin.model.entity.CategoryEntity;
import com.ben.domain.admin.model.entity.CategoryPageEntity;
import com.ben.domain.admin.repository.IAdminCategoryRepository;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-12  14:23
 * @Description: AdminCategoryRepositoryTest
 * @Version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminCategoryRepositoryTest {

    @Autowired
    private IAdminCategoryRepository repository;

    @Test
    public void test_insert() {
        repository.insert("Java");
    }

    @Test
    public void test_findCategoryPageList() throws ParseException {
        LocalDateTime beginDate = LocalDateTime.of(2024, 1, 2, 0, 0, 0);;
        LocalDateTime endDate =LocalDateTime.of(2024, 12, 12, 0, 0, 0);;
        Integer pageNum = 2;
        Integer pageSize = 5;

        String name = "前端";
        CategoryPageEntity categoryPageEntity = CategoryPageEntity.builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .name(name)
                .startDate(beginDate)
                .endDate(endDate).build();
        PageInfo<CategoryEntity> categoryPageList = repository.findCategoryPageList(categoryPageEntity);
        log.info("测试结果：{}", categoryPageList);
    }

    @Test
    public void test_deleteCategory() {
        int count = repository.deleteCategory(20L);

        log.info("测试结果：{}", count);
    }

    @Test
    public void test_findCategorySelectList() {
        List<CategoryEntity> categorySelectList = repository.findCategorySelectList();
        log.info("测试结果：{}", categorySelectList);

    }
}
