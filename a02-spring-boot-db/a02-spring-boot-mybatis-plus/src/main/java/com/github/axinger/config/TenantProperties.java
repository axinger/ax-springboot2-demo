package com.github.axinger.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 白名单配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "axinger.tenant")
@AllArgsConstructor
@NoArgsConstructor
public class TenantProperties {

    /**
     * 是否开启租户模式
     */
    private Boolean enable;

    /**
     * 多租户字段名称
     */
    private String column;

    /**
     * 需要排除的多租户的表
     */
    private List<String> exclusionTable = new ArrayList<>();

}

