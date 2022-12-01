package com.axing.demo;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class JWTTest {


    @Test
    public void test1() {

        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.MINUTE, 10);

        Map<String, Object> payload = new HashMap<String, Object>();
        //签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        //过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        //生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        //载荷
        payload.put("username", "jim");
        payload.put("password", "123456");

        String key = "aabb";
        String token = JWTUtil.createToken(payload, key.getBytes());
        System.out.println(token);

    }


    @Test
    public void test2() {
        String key = "aabb";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsIm5iZiI6MTY2MjI2MjczNCwiZXhwIjoxNjYyMjYzMzM0LCJpYXQiOjE2NjIyNjI3MzQsInVzZXJuYW1lIjoiamltIn0.R3XyoeEeYkEBiHHr7iacfnXbbX83UrFJmfa0VJhTzHU";
        JWT jwt = JWTUtil.parseToken(token);

        System.out.println(jwt.getPayload("username"));
        System.out.println("jwt.getPayload().getClaim(\"username\") = " + jwt.getPayload().getClaim("username"));
        boolean verifyKey = jwt.setKey(key.getBytes()).verify();
        System.out.println(verifyKey);

        boolean verifyTime = jwt.validate(0);
        System.out.println(verifyTime);


    }


}
