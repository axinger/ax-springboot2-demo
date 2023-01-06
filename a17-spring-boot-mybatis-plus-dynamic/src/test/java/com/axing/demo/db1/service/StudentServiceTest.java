package com.axing.demo.db1.service;

import com.axing.demo.db1.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @Test
    void test1() {
        List<Student> list = studentService.list();
        System.out.println("list = " + list);
    }
}
