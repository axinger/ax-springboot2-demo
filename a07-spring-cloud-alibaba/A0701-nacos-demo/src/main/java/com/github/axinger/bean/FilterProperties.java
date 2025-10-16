package com.github.axinger.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RefreshScope
@ConfigurationProperties(prefix = "axinger.filter")
public class FilterProperties {
    private List<String> blacklist = new ArrayList<>();
    private List<String> whitelist = new ArrayList<>();
}
