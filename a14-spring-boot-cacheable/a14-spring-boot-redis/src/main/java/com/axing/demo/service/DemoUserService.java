package com.axing.demo.service;

import com.axing.demo.model.User;

public interface DemoUserService {

    User findUser(Integer userId);

    User updateUser(User User);

    void deleteUser(Integer userId);

    User getLastName(Integer id);
}
