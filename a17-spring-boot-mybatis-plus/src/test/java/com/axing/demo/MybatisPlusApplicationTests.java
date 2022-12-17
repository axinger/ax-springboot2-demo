package com.axing.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

        List<School> List2 = schoolService.schoolList(Wrappers.<School>lambdaQuery()
                .eq(School::getId, 1));
        System.out.println("List2 = " + List2);
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
        // 可以 335ms
        List<Department> list1= departmentService.listLeft(Wrappers.query()
                .eq("d.id", 1));
        System.out.println("list1 = " + list1);
    }

    @Test
    void test5() {

        // 不可以
        List<Department> list2 = departmentService.listLeft(Wrappers.<Department>lambdaQuery()
                .eq(Department::getId, 1));
        System.out.println("list = " + list2);
    }

    @Test
    void test6() {

        // 可以
        List<Department> list2 = departmentService.listLeftSon(Wrappers.<Department>lambdaQuery()
                .eq(Department::getId, 1));
        System.out.println("list = " + list2);
    }

    @Test
    void test7_listAllSon() {

        // 不可以
        LambdaQueryWrapper<Department> wrapper = Wrappers.lambdaQuery();

        wrapper.eq(Department::getId, 1);
        List<Department> list2 = departmentService.listAllSon(wrapper);
        System.out.println("list = " + list2);
    }

    @Test
    void test7_listAllSon2() {

        // 不可以
        QueryWrapper<Object> wrapper = Wrappers.query()
                .eq("d.id", 1);

        List<Department> list2 = departmentService.listAllSon(wrapper);
        System.out.println("list = " + list2);
    }

}
