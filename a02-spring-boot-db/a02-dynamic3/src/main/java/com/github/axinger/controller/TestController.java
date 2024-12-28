package com.github.axinger.controller;

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
    private TestService testService;


    @GetMapping("/1")
    public void test3() {
        testService.test31();
    }


}
