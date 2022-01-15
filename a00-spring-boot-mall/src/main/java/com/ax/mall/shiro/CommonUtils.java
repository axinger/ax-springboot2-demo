// +----------------------------------------------------------------------
// | JavaWeb_Layui混编版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2021 上海JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <javaweb520@gmail.com>
// +----------------------------------------------------------------------
// | 免责声明:
// | 本软件框架禁止任何单位和个人用于任何违法、侵害他人合法利益等恶意的行为，禁止用于任何违
// | 反我国法律法规的一切平台研发，任何单位和个人使用本软件框架用于产品研发而产生的任何意外
// |  、疏忽、合约毁坏、诽谤、版权或知识产权侵犯及其造成的损失 (包括但不限于直接、间接、附带
// | 或衍生的损失等)，本团队不承担任何法律责任。本软件框架已申请版权保护，任何组织、单位和个
// | 人不得有任何侵犯我们的版权的行为(包括但不限于分享、转售、恶意传播，开源等等)，否则产生
// | 的一切后果和损失由侵权者全部承担，本软件框架只能用于公司和个人内部的法律所允许的合法合
// | 规的软件产品研发，详细声明内容请阅读《框架免责声明》附件；
// +----------------------------------------------------------------------

package com.ax.mall.shiro;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公共函数类
 */
public class CommonUtils {

    /**
     * 获取当前时间时间戳
     *
     * @return
     */
    public static Integer timeStamp() {
        long currentTime = System.currentTimeMillis();
        String time = String.valueOf(currentTime / 1000);
        return Integer.valueOf(time);
    }

    /**
     * 时间转为日期格式
     *
     * @param time
     * @param format
     * @return
     */
    public static String formatTime(Integer time, String format) {
        if (StringUtils.isEmpty(time)) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        long timeLong = time.longValue() * 1000;
        Date date = new Date(timeLong);
        return dateFormat.format(date);
    }


    /**
     * 验证邮箱是否正确
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号是否正确
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
            Matcher m = p.matcher(mobile);
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 生成指定位数的随机字符串
     *
     * @param isNum  是否是纯数字
     * @param length 长度
     * @return
     */
    public static String getRandomStr(boolean isNum, int length) {
        String resultStr = "";
        String str = isNum ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = str.length();
        boolean isStop = true;
        do {
            resultStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = str.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                resultStr += str.charAt(intR);
            }
            if (count >= 2) {
                isStop = false;
            }
        } while (isStop);
        return resultStr;
    }

    /**
     * 判断是否在数组中
     *
     * @param s
     * @param array
     * @return
     */
    public static boolean inArray(final String s, final String[] array) {
        for (String item : array) {
            if (item != null && item.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否在数组中
     *
     * @param key 键值
     * @param map Map对象
     * @return
     */
    public static boolean inArray2(String key, Map<String, String> map) {
        return map.containsKey(key);
    }

    /**
     * 从html中提取纯文本
     *
     * @param strHtml
     * @return
     */
    public static String stripHtml(String strHtml) {
        String content = strHtml.replaceAll("</?[^>]+>", ""); //剔出<html>的标签
        content = content.replaceAll("\\s*|\t|\r|\n", "");//去除字符串中的空格,回车,换行符,制表符
        return content;
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符等
     *
     * @param str 原始字符串
     * @return
     */
    public static String replaceSpecialStr(String str) {
        String repl = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            repl = m.replaceAll("");
        }
        return repl;
    }

    /**
     * 判断某个元素是否在数组中
     *
     * @param key 元素
     * @param map 数组
     * @return
     */
    public static boolean inArray(String key, Map<String, String> map) {
        boolean flag = false;
        for (String k : map.keySet()) {
            if (k.equals(key)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 对象转Map
     *
     * @param obj 对象
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }

    /**
     * MD5方法
     *
     * @param source
     * @return
     */
    public static String md5(byte[] source) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            StringBuffer buf = new StringBuffer();
            for (byte b : md.digest()) {
                buf.append(String.format("%02x", b & 0xff));
            }
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 密码加密
     *
     * @param password 密码
     * @return
     */
    public static String password(String password) {
        String md51 = md5(password.getBytes());
        String pwd = md5((md51 + "IgtUdEQJyVevaCxQnY").getBytes());
        return pwd;
    }

    /**
     * 对数组进行分组
     *
     * @param list 数据源
     * @param size 每组几个
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> split(List<T> list, Integer size) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        Integer count = list.size() / size;
        List<List<T>> arrayList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<T> temp = list.subList(i * size, (i + 1) * size);
            arrayList.add(temp);
        }
        Integer extra = list.size() % size;
        if (extra != 0) {
            List<T> temp = list.subList(count * size, count * size + extra);
            arrayList.add(temp);
        }
        return arrayList;
    }

    /**
     * 判断是否是JSON格式
     *
     * @param str JSON字符串
     * @return
     */
    private boolean isJson(String str) {
        try {
            JSONObject jsonStr = JSONObject.parseObject(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
