package com.ben.trigger.http.dto.setting;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  15:05
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = " 博客基础信息修改 DTO")
public class UpdateBlogSettingReqDTO {
    @NotBlank(message = "博客 LOGO 不能为空")
    private String logo;

    @NotBlank(message = "博客名称不能为空")
    private String name;

    @NotBlank(message = "博客作者不能为空")
    private String author;

    @NotBlank(message = "博客介绍语不能为空")
    private String introduction;

    @NotBlank(message = "博客头像不能为空")
    private String avatar;

    private String githubHomepage;

    private String csdnHomepage;

    private String giteeHomepage;

    private String zhihuHomepage;

    @Email(message = "邮箱格式不正确")
    private String mail;

    @NotNull(message = "请设置评论敏感词过滤是否开启")
    private Boolean isCommentSensiWordOpen;

    @NotNull(message = "请设置评论审核是否开启")
    private Boolean isCommentExamineOpen;
}
