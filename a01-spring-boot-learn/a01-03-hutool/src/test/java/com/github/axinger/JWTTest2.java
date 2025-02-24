package com.github.axinger;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.LocalDateTimeUtil;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class JWTTest2 {
//    private String secret = "YourSecretKey";
//    private long expiration = 86400000; // 1 day in milliseconds


    @Test
    void test() throws InterruptedException {
        String username = "admin";
//
//        assert Jwts.parser().verifyWith(key).build().parseSignedClaims(jws).getPayload().getSubject().equals("Joe");

        DateTime signTime = DateTime.now();
        DateTime expiresAt = signTime.offsetNew(DateField.SECOND, 2);

        String jwt = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(signTime)
                .setExpiration(expiresAt)
//                .signWith(SignatureAlgorithm.HS512, secret)
                .signWith(SignatureAlgorithm.HS256, "000000".getBytes())//加密方式,解密密码
                .compact();

        System.out.println("jwt = " + jwt);


//        TimeUnit.SECONDS.sleep(5);

        System.out.println("=============解析 JWT===========");
        try {
            Jws<Claims> result = Jwts.parser().setSigningKey("000000".getBytes()).parseClaimsJws(jwt);
            // 以下步骤随实际情况而定，只要上一行代码执行不抛异常就证明 jwt 是有效的、合法的
            Claims body = result.getBody();
            System.out.println("载荷-标准中注册的声明 id:" + body.getId());
            System.out.println("载荷-标准中注册的声明 subject:" + body.getSubject());
            System.out.println("载荷-标准中注册的声明 issueAt:" + body.getIssuedAt());
            LocalDateTime issued = LocalDateTimeUtil.of(body.getIssuedAt());
            System.out.println("issued = " + issued);

            LocalDateTime expiration = LocalDateTimeUtil.of(body.getExpiration());
            System.out.println("expiration = " + expiration);

            System.out.println("载荷-公共的声明的 id:" + result.getBody().get("id"));
            System.out.println("载荷-公共的声明的 name:" + result.getBody().get("name"));
            System.out.println("载荷-公共的声明的 sex:" + result.getBody().get("sex"));
        } catch (ExpiredJwtException e) {

            System.out.println("登陆过期,请重新登陆");
        } catch (UnsupportedJwtException e) {
            System.out.println("Token不合法,请自重");
        } catch (SignatureException e) {
            System.out.println("签名不正确");
        }

    }
}
