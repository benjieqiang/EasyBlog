package com.ben.domain.auth.handler;

import com.ben.domain.auth.utils.ResultUtil;
import com.ben.types.enums.ResponseCode;
import com.ben.types.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-05  18:42
 * @Description: 登录成功访问受保护资源，但是权限不够
 * @Version: 1.0
 */
@Slf4j
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn("登录成功访问收保护的资源，但是权限不够: ", accessDeniedException);
        // 预留，后面引入多角色时会用到
        ResultUtil.fail(response, Response.fail(ResponseCode.FORBIDDEN));
    }
}

