package com.axing.demo.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.axing.demo.entity.Userinfo;
import com.axing.demo.service.ITokenService;

public class TokenServiceImpl implements ITokenService {

    @Override
    public String getToken(Userinfo user) {
        String token = JWT.create().withAudience(user.getId().toString())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}