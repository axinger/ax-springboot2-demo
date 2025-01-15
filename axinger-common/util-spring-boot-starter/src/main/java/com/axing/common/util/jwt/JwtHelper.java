package com.axing.common.util.jwt;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xing
 */
public class JwtHelper {


    public static String tokenSignKey = "user";
    public static long tokenExpires = 24 * 60 * 60;//一天

    static String prefix = "Bearer ";

    /**
     * 签发JWT
     */
    public static String createToken(String id, String userName) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", id);
        map.put("userName", userName);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + tokenExpires); // 1 天有效期

        map.put("expire_time", expiryDate);

        final String token = cn.hutool.jwt.JWTUtil.createToken(map, tokenSignKey.getBytes());

        return prefix + token;
    }

    /**
     * 验证JWT
     *
     * @param token
     * @return
     */
    public static boolean validateToken(String bearer) {
        String token = StrUtil.removePrefix(bearer, prefix);

        return cn.hutool.jwt.JWTUtil.verify(token, tokenSignKey.getBytes());
    }

    // 根据token字符串得到用户id
    public static String getUserId(String token) {
        if (ObjectUtil.isEmpty(token)) {
            return null;
        }
        final JWT jwt = cn.hutool.jwt.JWTUtil.parseToken(token);
        String userId = (String) jwt.getPayload("userId");
        return userId;
    }

    public static String getUserName(String bearer) {
        String token = StrUtil.removePrefix(bearer, prefix);
        if (ObjectUtil.isEmpty(token)) {
            return null;
        }
        final JWT jwt = cn.hutool.jwt.JWTUtil.parseToken(token);
        String userName = (String) jwt.getPayload("userName");
        return userName;
    }

}
