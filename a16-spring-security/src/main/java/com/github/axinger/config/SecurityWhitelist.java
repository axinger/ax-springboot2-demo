package com.github.axinger.config;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecurityWhitelist {
    public static final List<String> WHITELIST = List.of("/login", "/","/favicon.ico");


    public boolean isWhitelisted(String path) {
        return WHITELIST.stream().anyMatch(path::matches); // 支持通配符匹配
    }
}
