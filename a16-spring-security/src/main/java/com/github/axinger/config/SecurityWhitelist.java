package com.github.axinger.config;

import java.util.List;

public class SecurityWhitelist {
    public static final List<String> WHITELIST = List.of("/login", "/favicon.ico");


    public static boolean isWhitelisted(String path) {
        return WHITELIST.stream().anyMatch(path::matches); // 支持通配符匹配
    }
}
