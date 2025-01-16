package com.axing.common.util.jwt;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author xing
 */
@Slf4j
public class JwtHelper {


    private static final String tokenSignKey = "abc123";
    private static final long tokenExpires = 24 * 60 * 60;//一天
    private static final String AuthorizationPrefix = "Bearer ";
    private static final String userId = "userId";
    private static final String username = "userName ";
    private static final String expireTime = "expireTime ";

    /**
     * 签发JWT
     */
    public static String createToken(String id, String userName) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + tokenExpires); // 1 天有效期

        Map<String, Object> map = new HashMap<>();
        map.put(userId, id);
        map.put(username, userName);
//        map.put(expireTime, expiryDate);

        map.put(JWTPayload.ISSUER, "axing");
        map.put(JWTPayload.SUBJECT, "all");
        map.put(JWTPayload.AUDIENCE, "to");
//        payload.put(JWTPayload.JWT_ID, "to");
        // jwt的签发时间
        map.put(JWTPayload.ISSUED_AT, now);
        // jwt的过期时间，这个过期时间必须要大于签发时间
        map.put(JWTPayload.EXPIRES_AT, expiryDate);
        // 生效时间
        map.put(JWTPayload.NOT_BEFORE, now);

        final String token = JWTUtil.createToken(map, tokenSignKey.getBytes());
        String authorization = AuthorizationPrefix + token;
        log.info("生成 Authorization: {}", authorization);
        return authorization;
    }

    ///  验证是否过期
    public static boolean validateToken(String bearer) {
        String token = StrUtil.removePrefix(bearer, AuthorizationPrefix);
        try {
            ///  验证是否过期
            boolean validate = JWT.of(token).setKey(tokenSignKey.getBytes()).validate(0);
            log.info("token 验证失效: {}", validate);
            return validate;
//            return JWTUtil.verify(token, tokenSignKey.getBytes());
        } catch (Exception e) {
            return false;
        }
    }

    // 根据token字符串得到用户id
    public static String getUserId(String token) {
        if (Objects.isNull(token)) {
            return null;
        }
        final JWT jwt = JWTUtil.parseToken(token);
        return Optional.ofNullable(jwt.getPayload(userId)).map(String::valueOf).orElse(null);
    }

    public static String getUserName(String bearer) {
        String token = StrUtil.removePrefix(bearer, AuthorizationPrefix);
        if (Objects.isNull(token)) {
            return null;
        }
        final JWT jwt = JWTUtil.parseToken(token);
        return Optional.ofNullable(jwt.getPayload(username)).map(String::valueOf).orElse(null);
    }

}
