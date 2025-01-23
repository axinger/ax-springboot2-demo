package com.github.axinger.config;

import com.github.axinger.util.CollectionUtils;

import java.util.List;

public class SecurityWhitelist {
    public static final List<String> WHITELIST = List.of("/login", "/favicon.ico", "/**/test1");

    public static boolean isWhitelisted(String path) {
        return CollectionUtils.matchesPath(WHITELIST, path);
//        return WHITELIST.stream().anyMatch(pattern -> matchesPattern(path, pattern));
    }
//
//    private static boolean matchesPattern(String path, String pattern) {
//        // 将 ** 替换为 .*?，以支持通配符匹配
//        String regexPattern = pattern.replace("/**/", "/.*?");
//        // 在正则表达式的两端加上 ^ 和 $，确保完整匹配路径
//        regexPattern = "^" + regexPattern + "$";
//        return path.matches(regexPattern);
//    }
//
//    public static void main(String[] args) {
//        // Test cases
//        System.out.println(isWhitelisted("/login")); // true
//        System.out.println(isWhitelisted("/favicon.ico")); // true
//        System.out.println(isWhitelisted("/user/test1")); // true
//        System.out.println(isWhitelisted("/admin/test1")); // true
//        System.out.println(isWhitelisted("/test1/123")); // false
//        System.out.println(isWhitelisted("/some/other/path")); // false
//
////        boolean b = com.github.axinger.util.CollectionUtils.matchesPath(WHITELIST, "/admin/test1");
////        System.out.println("b = " + b);
//    }
}
