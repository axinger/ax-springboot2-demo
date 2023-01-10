package com.axing.demo;

import com.axing.demo.db2.domain.Department;
import com.axing.demo.db2.service.DepartmentService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DBDemo3ApplicationTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    void test1() {
        List<Department> list2 = departmentService.list();
        System.out.println("list = " + list2);
    }

    @Test
    void test_Department_listLeftSon_可以() {
        // 可以
        List<Department> list2 = departmentService.listLeftEmployee(Wrappers.<Department>lambdaQuery()
                .eq(Department::getId, 1));
        System.out.println("list = " + list2);
    }


}
