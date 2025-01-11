package com.github.axinger.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@Data
@ConfigurationProperties(prefix = "axing.person")
@RefreshScope
public class PersonProperties {
    private String name;
    private String age;
}
