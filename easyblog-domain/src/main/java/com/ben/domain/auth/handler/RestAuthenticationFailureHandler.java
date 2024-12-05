package com.ben.domain.auth.handler;

import com.ben.domain.auth.exception.UsernameOrPasswordNullException;
import com.ben.domain.auth.utils.ResultUtil;
import com.ben.types.enums.ResponseCode;
import com.ben.types.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-05  14:11
 * @Description: 认证失败处理器
 * @Version: 1.0
 */
@Component
@Slf4j
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.warn("RestAuthenticationFailureHandler#AuthenticationException: ", exception);
        if (exception instanceof UsernameOrPasswordNullException) {
            // 用户名/密码为空
            ResultUtil.fail(response, Response.fail(exception.getMessage()));
        } else if (exception instanceof BadCredentialsException) {
            // 用户名/密码错误
            ResultUtil.fail(response, Response.fail(ResponseCode.USERNAME_OR_PWD_ERROR));
        }

        // 其他情况：登录失败
        ResultUtil.fail(response, Response.fail(ResponseCode.LOGIN_FAIL));
    }
}