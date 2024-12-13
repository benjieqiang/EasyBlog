package com.ben.test.infrastructure.repository;

import com.ben.domain.admin.model.entity.BlogSettingEntity;
import com.ben.domain.admin.repository.IAdminBlogSettingRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  15:13
 * @Description: AdminBlogSettingRepositoryTest
 * @Version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminBlogSettingRepositoryTest {
    @Autowired
    private IAdminBlogSettingRepository repository;

    @Test
    public void test_updateSetting() {
        BlogSettingEntity blogSettingEntity = BlogSettingEntity.builder()
                .logo("jjdd")
                .name("joey")
                .author("Joey")
                .introduction("less is less")
                .avatar("http://www")
                .githubHomepage("http://github.com/")
                .giteeHomepage("http://gitee.com")
                .zhihuHomepage("http:zhihu.com")
                .csdnHomepage("http://csdn.com")
                .mail("ebenn@qq.com")
                .isCommentExamineOpen(true)
                .isCommentSensiWordOpen(true)
                .build();
        repository.update(blogSettingEntity);
    }

    @Test
    public void test_insert() {
        BlogSettingEntity blogSettingEntity = new BlogSettingEntity();
        blogSettingEntity.setAuthor("Wang");
        repository.insert(blogSettingEntity);
    }

    @Test
    public void test_findDetail() {
        BlogSettingEntity blogSetting = repository.findBlogSetting(1L);
        log.info("测试结果：{}", blogSetting);
    }

}
