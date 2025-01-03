package com.ben.domain.web.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  11:39
 * @Description: 博客设置entity
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogSettingEntity {

    /* id */
    private Long id;
    /* 博客Logo */
    private String logo;
    /* 博客名称 */
    private String name;
    /* 作者名 */
    private String author;
    /* 介绍语 */
    private String introduction;
    /* 作者头像 */
    private String avatar;
    /* GitHub 主页访问地址 */
    private String githubHomepage;
    /* CSDN 主页访问地址 */
    private String csdnHomepage;
    /* Gitee 主页访问地址 */
    private String giteeHomepage;
    /* 知乎主页访问地址 */
    private String zhihuHomepage;
    /* 博主邮箱地址 */
    private String mail;
    /* 是否开启评论敏感词过滤, 0:不开启；1：开启 */
    private Boolean isCommentSensiWordOpen;
    /* 是否开启评论审核, 0: 未开启；1：开启 */
    private Boolean isCommentExamineOpen;

}
