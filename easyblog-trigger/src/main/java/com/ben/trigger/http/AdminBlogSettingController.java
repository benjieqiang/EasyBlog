package com.ben.trigger.http;

import com.ben.domain.admin.model.entity.BlogSettingEntity;
import com.ben.domain.admin.service.IAdminBlogSettingService;
import com.ben.trigger.http.dto.setting.UpdateBlogSettingReqDTO;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  15:03
 * @Description: Admin 博客设置模块
 * @Version: 1.0
 */
@RestController
@Slf4j
@Api(tags = "Admin 博客设置模块")
@RequestMapping("/api/${app.config.api-version}/admin/setting")
public class AdminBlogSettingController {

    @Autowired
    private IAdminBlogSettingService blogSettingService;

    @PostMapping("/update")
    @ApiOperation(value = "博客基础信息修改")
    @ApiOperationLog(description = "博客基础信息修改")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response updateSetting(@RequestBody @Validated UpdateBlogSettingReqDTO updateBlogSettingReqDTO) {
        BlogSettingEntity blogSettingEntity = BlogSettingEntity.builder()
                .logo(updateBlogSettingReqDTO.getLogo())
                .name(updateBlogSettingReqDTO.getName())
                .author(updateBlogSettingReqDTO.getAuthor())
                .introduction(updateBlogSettingReqDTO.getIntroduction())
                .avatar(updateBlogSettingReqDTO.getAvatar())
                .githubHomepage(updateBlogSettingReqDTO.getGithubHomepage())
                .csdnHomepage(updateBlogSettingReqDTO.getCsdnHomepage())
                .giteeHomepage(updateBlogSettingReqDTO.getGiteeHomepage())
                .zhihuHomepage(updateBlogSettingReqDTO.getZhihuHomepage())
                .mail(updateBlogSettingReqDTO.getMail())
                .isCommentSensiWordOpen(updateBlogSettingReqDTO.getIsCommentSensiWordOpen())
                .isCommentExamineOpen(updateBlogSettingReqDTO.getIsCommentExamineOpen())
                .build();

        blogSettingService.updateBlogSetting(blogSettingEntity);
        return Response.success();
    }

    @GetMapping("/detail")
    @ApiOperation(value = "获取博客设置详情")
    @ApiOperationLog(description = "获取博客设置详情")
    public Response findDetail() {
        BlogSettingEntity detail = blogSettingService.findDetail(1L);
        return Response.success(detail);
    }

}
