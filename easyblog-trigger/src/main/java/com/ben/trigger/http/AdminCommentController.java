package com.ben.trigger.http;

import com.ben.domain.admin.service.IAdminCommentService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-06  10:47
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@Slf4j
@Api(tags = "Admin 评论模块")
@RequestMapping("/api/${app.config.api-version}/admin/comment")
public class AdminCommentController {
    @Autowired
    private IAdminCommentService adminCommentService;


}
