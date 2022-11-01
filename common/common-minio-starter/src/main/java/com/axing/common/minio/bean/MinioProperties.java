package com.axing.common.minio.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 配置属性
 *
 * @author luoyu
 */
@Data
@ConfigurationProperties(prefix = "axing.minio")
public class MinioProperties {

    private String url = "http://localhost:9000";

    /**
     * Access key就像用户ID，可以唯一标识你的账户
     */
    private String accessKey;

    /**
     * Secret key是你账户的密码
     */
    private String secretKey;
}
