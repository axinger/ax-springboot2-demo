package com.github.axinger.model.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "user")
public class UserProperties {
    private String username;
    private String password;
    private List<String> tip;
}
