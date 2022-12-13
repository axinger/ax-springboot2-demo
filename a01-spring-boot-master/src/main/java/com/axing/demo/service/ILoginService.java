package com.axing.demo.service;

import com.axing.demo.entity.Userinfo;
import com.axing.demo.util.error.TokenException;
import jakarta.servlet.http.HttpServletRequest;

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
