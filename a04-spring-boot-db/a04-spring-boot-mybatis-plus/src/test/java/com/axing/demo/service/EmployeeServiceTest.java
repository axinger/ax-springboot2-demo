package com.axing.demo.service;

import com.axing.demo.domain.Employee;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void test_list() {
        List<Employee> list = employeeService.list();
        System.out.println("list = " + list);
    }

    @Test
    void test_list2() {
        List<Employee> list = employeeService.leftDepartmentList(Wrappers.<Employee>lambdaQuery());
        System.out.println("list = " + list);
    }

    @Test
    void test6_2() {
        List<Employee> list = employeeService.leftDepartmentList(Wrappers.<Employee>lambdaQuery()
                .eq(Employee::getDeptId, 1)
                .last("limit 1")
        );
        System.out.println("list = " + list);
    }
}
