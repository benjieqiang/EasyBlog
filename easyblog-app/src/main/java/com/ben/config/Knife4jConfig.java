package com.ben.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-02  11:09
 * @Description: Knife4j 配置
 * @Version: 1.0
 */
@Configuration
@EnableSwagger2WebMvc
@Profile("dev") // only in dev
public class Knife4jConfig {
    @Bean("webApi")
    public Docket createWebApiDoc() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                // 分组名称
                .groupName("EasyBlog-接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ben.trigger.http")) // 这里指定 Controller 扫描包路径
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * 构建 API 信息
     *
     * @return
     */
    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("EasyBlog 博客接口文档") // 标题
                .description("EasyBlog 是一款由 Spring Boot + React 开发的前后端分离博客。") // 描述
                .termsOfServiceUrl("https://www.benjieqiang.com/") // API 服务条款
                .contact(new Contact("Ben", "https://www.benjieqiang.com", "benjieqiang@gmail.com")) // 联系人
                .version("1.0") // 版本号
                .build();
    }
}
