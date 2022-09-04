package com.axing.demo.controller;

import com.axing.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName IndexController.java
 * @description TODO
 * @createTime 2022年07月21日 21:51:00
 */
@RestController
public class IndexController {

    @Autowired
    StudentService studentService;


    @GetMapping("/")
    public List index() {
        return studentService.list();
    }
}
