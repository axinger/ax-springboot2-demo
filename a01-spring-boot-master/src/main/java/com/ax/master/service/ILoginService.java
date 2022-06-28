package com.ax.master.service;

import com.ax.master.entity.Userinfo;
import com.ax.master.util.error.TokenException;

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
