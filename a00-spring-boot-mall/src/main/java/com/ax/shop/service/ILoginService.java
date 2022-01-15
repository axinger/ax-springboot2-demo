package com.ax.shop.service;

import com.ax.shop.entity.Userinfo;
import com.ax.shop.util.error.TokenException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author axing
 */
public interface ILoginService {


    Userinfo login(String username,
                   String password,
                   HttpServletRequest request) throws TokenException;


    boolean hasAdmin();

    void createAdmin();

    Userinfo getByUserName(String username);

    Userinfo getById(Long id);
}
