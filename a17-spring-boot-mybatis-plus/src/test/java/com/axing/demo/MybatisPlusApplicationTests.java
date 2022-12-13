package com.axing.demo;

import com.axing.demo.domain.Student;
import com.axing.demo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private StudentService studentService;

    @Test
    void test1() {
        List<Student> list = studentService.lambdaQuery().list();
        System.out.println("list = " + list);
    }
}
