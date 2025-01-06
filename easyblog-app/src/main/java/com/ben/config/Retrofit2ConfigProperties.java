package com.ben.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "gateway.config", ignoreInvalidFields = true)
public class Retrofit2ConfigProperties {

    /**
     * 状态: true = 开启, false = 关闭
     */
    private boolean enable;
    /**
     * 转发地址
     */
    private String host;

}
