package com.axing.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 配置属性
 *
 * @author luoyu
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    /**
     * 对象存储服务的URL
     */
    private String endpoint;

    /**
     * Access key就像用户ID，可以唯一标识你的账户
     */
    private String accessKey;

    /**
     * Secret key是你账户的密码
     */
    private String secretKey;

}
