package com.axing.demo;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.axing.demo.domain.Department;
import com.axing.demo.domain.Room;
import com.axing.demo.domain.School;
import com.axing.demo.service.DepartmentService;
import com.axing.demo.service.RoomService;
import com.axing.demo.service.SchoolService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private RoomService roomService;

    @Test
    void test() {

        List<School> list = schoolService.list();
        System.out.println("list = " + list);

        List<School> list1 = schoolService.schoolList(Wrappers.<School>query()
                .eq("s.id", 1));
        System.out.println("list1 = " + list1);
    }

    @Test
    void test2() {

        List<Room> list = roomService.list();
        System.out.println("list = " + list);
    }

    @Autowired
    DepartmentService departmentService;

    @Test
    void test3() {
        // List<Department> list = departmentService.getDepartmentByEmployee(Wrappers.<Department>lambdaQuery()
        //         .eq(Department::getId, 1));

        List<Department> list = departmentService.getDepartmentByEmployee(Wrappers.query()
                .eq("d.id", 1));
        System.out.println("list = " + list);

    }
    @Test
    void test4() {
        List<Department> list2 = departmentService.listLeft(Wrappers.query()
                .eq("d.id", 1));
        System.out.println("list = " + list2);

    }
}
