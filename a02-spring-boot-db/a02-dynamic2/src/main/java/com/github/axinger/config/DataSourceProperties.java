package com.github.axinger.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "spring.datasource.dynamic")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@Component
public class DataSourceProperties {
    private Map<String, DataSourceBean> datasource = new HashMap<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Component
    public static class DataSourceBean {
        private String url;
        private String username;
        private String password;
        private String driverClassName;
    }
}
