package com.axing.demo.service;

import com.axing.demo.entity.Userinfo;

public interface ITokenService {

    String getToken(Userinfo user);
}
