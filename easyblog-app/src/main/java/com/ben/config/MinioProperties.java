package com.ben.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author benjieqiang
 * @description minio文件配置类
 * @date 2024/12/13 4:34 PM
 */
@Data
@ConfigurationProperties(prefix = "minio", ignoreInvalidFields = true)
@Component
public class MinioProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
