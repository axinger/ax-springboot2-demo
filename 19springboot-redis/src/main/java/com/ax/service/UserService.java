package com.ax.service;

import com.ax.model.Person;

public interface UserService {

    Person findUser(Integer userId);

    Person updateUser(Person person);

    void deleteUser(Integer userId);
}
