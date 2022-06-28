package com.ax.master.service;

import com.ax.master.entity.Userinfo;

public interface ITokenService {

    String getToken(Userinfo user);
}
