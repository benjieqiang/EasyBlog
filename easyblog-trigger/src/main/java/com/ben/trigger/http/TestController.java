package com.ben.trigger.http;

import com.ben.trigger.http.dto.UserDTO;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.response.Response;
import com.ben.types.utils.JsonUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-02  11:14
 * @Description: 测试Controller
 * @Version: 1.0
 */
@RestController
@Slf4j
@Api(tags="测试模块")
public class TestController {
    @PostMapping("/admin/test")
    @ApiOperationLog(description = "测试接口")
    public Response test(@RequestBody @Validated UserDTO user) {
        // 主动定义一个运行时异常，分母不能为零, 会被全局异常捕获，返回自定义信息
//        int i = 1 / 0;
        // 打印入参
        log.info(JsonUtil.toJsonString(user));

        // 测试jackson
//        // 设置三种日期字段值
//        user.setCreateTime(LocalDateTime.now());
//        user.setUpdateDate(LocalDate.now());
//        user.setTime(LocalTime.now());

        return Response.success(user);
    }


    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/admin/hello")
    public String hello2(){
        return "hello";
    }
}
