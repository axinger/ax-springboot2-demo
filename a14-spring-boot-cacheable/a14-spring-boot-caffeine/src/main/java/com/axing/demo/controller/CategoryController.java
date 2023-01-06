package com.axing.demo.controller;

import com.axing.demo.domain.Student;
import com.axing.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class CategoryController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public List<Student> list() {
        return studentService.list();
    }

    @GetMapping("/one")
    public Student one(Long id) {
        return studentService.getById(id);
    }


}

