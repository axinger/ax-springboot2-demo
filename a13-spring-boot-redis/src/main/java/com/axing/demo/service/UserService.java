package com.axing.demo.service;

import com.axing.demo.model.Person;

public interface UserService {

    Person findUser(Integer userId);

    Person updateUser(Person person);

    void deleteUser(Integer userId);
}
