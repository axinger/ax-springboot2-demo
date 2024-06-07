package com.github.axinger.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.application")
//@EnableConfigurationProperties(ApplicationInfo.class)
public class ApplicationInfo {

    private String version;
    private String description;
}
