package com.axing.common.doc.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "doc-info")
public class DocInfo {
    private String title = "阿星项目";
    private String description = "阿星springboot开发项目";
    private String version = "v0.0.1";
    private String websiteName = "项目地址";
    private String websiteUrl = "https://github.com/axinger";
}