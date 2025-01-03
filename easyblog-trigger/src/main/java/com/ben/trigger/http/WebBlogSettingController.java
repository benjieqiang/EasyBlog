package com.ben.trigger.http;

import com.ben.domain.web.model.entity.BlogSettingEntity;
import com.ben.domain.web.service.IBlogSettingService;
import com.ben.trigger.http.dto.setting.WebFindBlogSettingRspDTO;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-02  17:27
 * @Description: 前台
 * @Version: 1.0
 */
@RestController
@Slf4j
@Api(tags = "前台 博客设置模块")
@RequestMapping("/api/${app.config.api-version}/blog/setting")
public class WebBlogSettingController {
    @Autowired
    private IBlogSettingService blogSettingService;

    @GetMapping("/detail")
    @ApiOperation(value = "获取博客设置详情")
    @ApiOperationLog(description = "获取博客设置详情")
    public Response findDetail() {
        BlogSettingEntity detail = blogSettingService.findDetail(1L);
        WebFindBlogSettingRspDTO blogSettingRspDTO = WebFindBlogSettingRspDTO.builder()
                .logo(detail.getLogo())
                .name(detail.getName())
                .author(detail.getAuthor())
                .introduction(detail.getIntroduction())
                .avatar(detail.getAvatar())
                .githubHomepage(detail.getGithubHomepage())
                .csdnHomepage(detail.getCsdnHomepage())
                .giteeHomepage(detail.getGiteeHomepage())
                .zhihuHomepage(detail.getZhihuHomepage())
                .mail(detail.getMail())
                .build();
        return Response.success(blogSettingRspDTO);
    }
}
