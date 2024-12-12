package com.ben.trigger.http;

import com.ben.domain.admin.service.IAdminUserService;
import com.ben.trigger.http.dto.user.AdminUserDTO;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.enums.ResponseCode;
import com.ben.types.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-05  22:34
 * @Description: Admin 用户模块
 * @Version: 1.0
 */
@RestController
@Slf4j
@Api(tags = "Admin 用户模块")
@RequestMapping("/api/${app.config.api-version}/admin/")
public class AdminUserController {

    @Autowired
    private IAdminUserService userService;

    @PostMapping("/password/update")
    @ApiOperation(value = "修改用户密码")
    @ApiOperationLog(description = "修改用户密码")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response updatePassword(@RequestBody @Validated AdminUserDTO adminUserDTO) {
        String username = adminUserDTO.getUsername();
        String password = adminUserDTO.getPassword();
        int count = userService.updatePassword(username, password);
        if (count == 0) {
            return Response.fail(ResponseCode.USERNAME_NOT_FOUND);
        }
        return Response.success();
    }

    @PostMapping("/user/info")
    @ApiOperation(value = "获取用户信息")
    @ApiOperationLog(description = "获取用户信息")
    public Response findUserInfo() {
        String username = userService.findUserInfo();
        return Response.success(username);
    }

}
