package com.github.axinger.controller;

import com.github.axinger.db.master.service.PersonService;
import com.github.axinger.db.slave.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName TestController.java
 * @description TODO
 * @createTime 2022年07月27日 10:56:00
 */
@RestController
public class TestController {

    @Autowired
    private PersonService personService;

    @Autowired
    private DogService dogService;

    @GetMapping("/1")
    public Object test1() {
        return personService.list();
    }

    @GetMapping("/2")
    public Object test2() {
        return dogService.list();
    }
}
