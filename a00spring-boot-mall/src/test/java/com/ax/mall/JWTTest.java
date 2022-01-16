package com.ax.mall;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class JWTTest {


    @Test
    public void test1() {

        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;

            {
                put("uid", Integer.parseInt("123"));
                put("expire_time", System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15);
            }
        };

        String token = JWTUtil.createToken(map, "1234".getBytes());

        System.out.println("token = " + token);


    }


    @Test
    public void test2() {
        String rightToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjEyMywiZXhwaXJlX3RpbWUiOjE2Mzk3MzYwNzY0OTh9.GKXcpLAqNw9CZUSHOzvCFsDllXLDcVebPgOY8cQXUH8";

        final JWT jwt = JWTUtil.parseToken(rightToken);

        Object header = jwt.getHeader(JWTHeader.TYPE);
        System.out.println("header = " + header);
        Object sub = jwt.getPayload("uid");
        System.out.println("sub = " + sub);


        boolean verify = JWTUtil.verify(rightToken, "1234".getBytes());
        System.out.println("verify = " + verify);


    }

    @Test
    public void test3() {

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE2MjQwMDQ4MjIsInVzZXJJZCI6MSwiYXV0aG9yaXRpZXMiOlsiUk9MRV_op5LoibLkuozlj7ciLCJzeXNfbWVudV8xIiwiUk9MRV_op5LoibLkuIDlj7ciLCJzeXNfbWVudV8yIl0sImp0aSI6ImQ0YzVlYjgwLTA5ZTctNGU0ZC1hZTg3LTVkNGI5M2FhNmFiNiIsImNsaWVudF9pZCI6ImhhbmR5LXNob3AifQ." +
                "aixF1eKlAKS_k3ynFnStE7-IRGiD5YaqznvK2xEjBew";

        boolean verify = JWTUtil.verify(token, "123456".getBytes());
        System.out.println("verify = " + verify);
    }
}
