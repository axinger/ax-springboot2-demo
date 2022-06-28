package com.ax.master.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ax.master.entity.Userinfo;
import com.ax.master.service.ITokenService;

public class TokenServiceImpl implements ITokenService {

    @Override
    public String getToken(Userinfo user) {
        String token = JWT.create().withAudience(user.getId().toString())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
