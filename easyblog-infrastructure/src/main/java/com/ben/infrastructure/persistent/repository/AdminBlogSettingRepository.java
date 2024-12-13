package com.ben.infrastructure.persistent.repository;

import com.ben.domain.admin.model.entity.BlogSettingEntity;
import com.ben.domain.admin.repository.IAdminBlogSettingRepository;
import com.ben.infrastructure.persistent.dao.IBlogSettingDao;
import com.ben.infrastructure.persistent.po.BlogSetting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  14:51
 * @Description: 博客设置仓储
 * @Version: 1.0
 */
@Slf4j
@Component
public class AdminBlogSettingRepository implements IAdminBlogSettingRepository {
    @Autowired
    private IBlogSettingDao blogSettingDao;

    @Override
    public void insert(BlogSettingEntity blogSettingEntity) {
        BlogSetting blogSetting = BlogSetting.builder()
                .logo(blogSettingEntity.getLogo())
                .name(blogSettingEntity.getName())
                .author(blogSettingEntity.getAuthor())
                .introduction(blogSettingEntity.getIntroduction())
                .avatar(blogSettingEntity.getAvatar())
                .githubHomepage(blogSettingEntity.getGithubHomepage())
                .csdnHomepage(blogSettingEntity.getCsdnHomepage())
                .giteeHomepage(blogSettingEntity.getGiteeHomepage())
                .zhihuHomepage(blogSettingEntity.getZhihuHomepage())
                .mail(blogSettingEntity.getMail())
                .isCommentSensiWordOpen(blogSettingEntity.getIsCommentSensiWordOpen())
                .isCommentExamineOpen(blogSettingEntity.getIsCommentExamineOpen())
                .build();

        blogSettingDao.insert(blogSetting);
    }

    @Override
    public int update(BlogSettingEntity blogSettingEntity) {
        BlogSetting blogSetting = BlogSetting.builder()
                .id(1L)
                .logo(blogSettingEntity.getLogo())
                .name(blogSettingEntity.getName())
                .author(blogSettingEntity.getAuthor())
                .introduction(blogSettingEntity.getIntroduction())
                .avatar(blogSettingEntity.getAvatar())
                .githubHomepage(blogSettingEntity.getGithubHomepage())
                .csdnHomepage(blogSettingEntity.getCsdnHomepage())
                .giteeHomepage(blogSettingEntity.getGiteeHomepage())
                .zhihuHomepage(blogSettingEntity.getZhihuHomepage())
                .mail(blogSettingEntity.getMail())
                .isCommentSensiWordOpen(blogSettingEntity.getIsCommentSensiWordOpen())
                .isCommentExamineOpen(blogSettingEntity.getIsCommentExamineOpen())
                .build();
        return blogSettingDao.update(blogSetting);
    }

    @Override
    public BlogSettingEntity findBlogSetting(Long id) {
        BlogSetting blogSetting = blogSettingDao.selectBlogSetting(id);

        return BlogSettingEntity.builder()
                .logo(blogSetting.getLogo())
                .name(blogSetting.getName())
                .author(blogSetting.getAuthor())
                .introduction(blogSetting.getIntroduction())
                .avatar(blogSetting.getAvatar())
                .githubHomepage(blogSetting.getGithubHomepage())
                .csdnHomepage(blogSetting.getCsdnHomepage())
                .giteeHomepage(blogSetting.getGiteeHomepage())
                .zhihuHomepage(blogSetting.getZhihuHomepage())
                .mail(blogSetting.getMail())
                .isCommentSensiWordOpen(blogSetting.getIsCommentSensiWordOpen())
                .isCommentExamineOpen(blogSetting.getIsCommentExamineOpen())
                .build();
    }
}
