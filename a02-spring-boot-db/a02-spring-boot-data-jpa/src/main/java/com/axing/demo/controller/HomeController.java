package com.axing.demo.controller;

import com.axing.demo.dao.UsersJpaRepository;
import com.axing.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private UsersJpaRepository usersJpaRepository;

    @GetMapping("/data")
    public Object data() {
        return List.of(1);
    }

    @GetMapping("/list")
    public Object findAll() {
        List<Users> all = usersJpaRepository.findAll();
        return all;
    }
}
