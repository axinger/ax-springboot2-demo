package com.github.axinger.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Configuration
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "spring.datasource.dynamic")
public class DataSourceProperties {
    private Map<String, DataSourceBean> datasource = new HashMap<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Configuration
    public static class DataSourceBean {
        private String url;
        private String username;
        private String password;
        private String driverClassName;
    }
}
