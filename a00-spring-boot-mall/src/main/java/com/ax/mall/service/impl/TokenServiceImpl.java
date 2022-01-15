package com.ax.mall.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ax.mall.entity.Userinfo;
import com.ax.mall.service.ITokenService;

public class TokenServiceImpl implements ITokenService {

    @Override
    public String getToken(Userinfo user) {
        String token = JWT.create().withAudience(user.getId().toString())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
