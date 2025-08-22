package com.axing.common.util.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /// 格式化字符串, ,未匹配的:姓名=jim,年龄=20,性别={sex},保留占位符
    public static String format(CharSequence template, Map<String, Object> paramsMap) {
        // 使用正则匹配所有 {key} 形式的占位符
        Pattern pattern = Pattern.compile("\\{(.+?)}");
        Matcher matcher = pattern.matcher(template);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            // 提取占位符内的key
            String key = matcher.group(1);
            // 获取值
            Object value = paramsMap.get(key);
            if (value == null) {
                continue;
            }
            // 替换当前匹配项
            matcher.appendReplacement(result, value.toString());
        }
        // 添加剩余未匹配部分
        matcher.appendTail(result);
        return result.toString();
    }

    /// 占位符格式化方法,未匹配的:姓名=jim,年龄=20,性别={},保留占位符
    public static String format(CharSequence template, Object... params) {
        // 使用正则匹配所有 {} 形式的占位符
        Pattern pattern = Pattern.compile("\\{}");
        Matcher matcher = pattern.matcher(template);
        StringBuilder result = new StringBuilder();

        int index = 0;
        while (matcher.find()) {
            if (index >= params.length) {
                break;
            }
            Object value = params[index++];
            if (value == null) {
                continue;
            }
            matcher.appendReplacement(result, Matcher.quoteReplacement(value.toString()));
        }
        matcher.appendTail(result);
        return result.toString();
    }


    public static void main(String[] args) {
        String template = "姓名={name},年龄={age},性别={sex}";
        Map<String, Object> params = new HashMap<>();
        params.put("name", "jim");
        params.put("age", 20);
        System.out.println(StringUtil.format(template, params));

        System.out.println("===================");
        System.out.println(StringUtil.format("姓名={},年龄={},性别={}", "jim", 20));
    }
}
