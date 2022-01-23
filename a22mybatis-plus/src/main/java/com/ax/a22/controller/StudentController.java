package com.ax.a22.controller;

import com.ax.a22.service.TStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName StudentController.java
 * @Description TODO
 * @createTime 2022年01月23日 21:58:00
 */

@RestController
public class StudentController {

    @Autowired
    TStudentService service;

    @GetMapping("/")
    public void test() {
        System.out.println("service.getById(1) = " + service.getById(1));
    }
}
