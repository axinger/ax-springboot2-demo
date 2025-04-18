package com.github.axinger.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {
        XxlJobProperties.class,
})
public class MyAutoImportConfig {

}
