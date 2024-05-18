package com.github.axinger;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.axinger.db.domain.Department;
import com.github.axinger.db.service.DepartmentService;
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
