package com.axing.demo.service;

/**
 * @author axing
 */
public interface IRegisterService {

    boolean register(String username, String password, int userType);

    boolean checkUsername(String username);

}
