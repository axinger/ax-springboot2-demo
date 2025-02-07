package com.axing.common.util.utils;

import java.util.List;

public class CollectionUtils {


    public static boolean matchesPath(List<String> urls, String path) {
        return urls.stream().anyMatch(pattern -> matchesPattern(path, pattern));
    }

    private static boolean matchesPattern(String path, String pattern) {
        // 将 ** 替换为 .*?，以支持通配符匹配
        String regexPattern = pattern.replace("/**/", "/.*?");
        // 在正则表达式的两端加上 ^ 和 $，确保完整匹配路径
        regexPattern = "^" + regexPattern + "$";
        return path.matches(regexPattern);
    }

}
