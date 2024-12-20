package com.github.axinger.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationProperties(prefix = "spring.application")
//@Configuration
@EnableConfigurationProperties(value = {ApplicationInfo.class})
public record ApplicationInfo(String version, String description) {


}
