package com.ax.mall.service;

import com.ax.mall.entity.Userinfo;

public interface ITokenService {

    String getToken(Userinfo user);
}
