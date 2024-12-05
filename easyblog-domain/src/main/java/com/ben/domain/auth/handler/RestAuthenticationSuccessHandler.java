package com.ben.domain.auth.handler;

import com.ben.domain.auth.model.valobj.LoginRspVO;
import com.ben.domain.auth.utils.JwtUtil;
import com.ben.domain.auth.utils.ResultUtil;
import com.ben.types.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-05  14:08
 * @Description: 认证成功处理器
 * @Version: 1.0
 */
@Component
@Slf4j
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 从 authentication 对象中获取用户的 UserDetails 实例，即获取用户名
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 通过用户名生成 Token
        String username = userDetails.getUsername();
        String token = jwtUtil.generateToken(username);

        // 返回 Token
        LoginRspVO loginRspVO = LoginRspVO.builder().token(token).build();

        ResultUtil.ok(response, Response.success(loginRspVO));
    }
}