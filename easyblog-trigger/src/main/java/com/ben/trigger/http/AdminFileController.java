package com.ben.trigger.http;

import com.ben.domain.admin.service.IAdminFileService;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  17:11
 * @Description: Admin 文件模块
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/${app.config.api-version}/admin/file")
@Api(tags = "Admin 文件模块")
public class AdminFileController {

    @Autowired
    private IAdminFileService fileService;

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传")
    @ApiOperationLog(description = "文件上传")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response uploadFile(@RequestParam MultipartFile file) {
        return Response.success(fileService.uploadFile(file));
    }

}