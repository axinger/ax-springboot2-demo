package com.axing.demo;

import com.axing.demo.domain.Department;
import com.axing.demo.domain.Employee;
import com.axing.demo.domain.Room;
import com.axing.demo.domain.School;
import com.axing.demo.service.DepartmentService;
import com.axing.demo.service.EmployeeService;
import com.axing.demo.service.RoomService;
import com.axing.demo.service.SchoolService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SchoolService schoolService;
    @Autowired
    private RoomService roomService;

    @Test
    void test_School_schoolList_可以() {

        List<School> list = schoolService.list();
        System.out.println("list = " + list);

        List<School> list1 = schoolService.schoolList(Wrappers.<School>query()
                .eq("s.id", 1));
        System.out.println("list1 = " + list1);

        // 这个不可以
        // List<School> List2 = schoolService.schoolList(Wrappers.<School>lambdaQuery()
        //         .eq(School::getId, 1));
        // System.out.println("List2 = " + List2);
    }

    @Test
    void test2() {
        List<Room> list = roomService.list();
        System.out.println("list = " + list);
    }

    @Test
    void test3() {
        // List<Department> list = departmentService.getDepartmentByEmployee(Wrappers.<Department>lambdaQuery()
        //         .eq(Department::getId, 1));

        List<Department> list = departmentService.getDepartmentByEmployee(Wrappers.query()
                .eq("d.id", 1));
        System.out.println("list = " + list);

    }

    @Test
    void test_Department_listLeft_可以() {
        // 可以 335ms
        List<Department> list1 = departmentService.listLeft(Wrappers.query()
                .eq("d.id", 1));
        System.out.println("list1 = " + list1);
    }

    @Test
    void test_Department_listLeft_不可以() {

        // 不可以
        List<Department> list2 = departmentService.listLeft(Wrappers.<Department>lambdaQuery()
                .eq(Department::getId, 1));
        System.out.println("list = " + list2);
    }

    @Test
    void test_Department_listLeftSon_可以() {

        // 可以
        List<Department> list2 = departmentService.listLeftSon(Wrappers.<Department>lambdaQuery()
                .eq(Department::getId, 1));
        System.out.println("list = " + list2);
    }

    @Test
    void test6_2() {
        List<Employee> list = employeeService.listLeftSon(Wrappers.<Department>lambdaQuery());
        System.out.println("list = " + list);
    }

    @Test
    void test7_Department_listAllSon_不可以() {
        // 不可以
        LambdaQueryWrapper<Department> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Department::getId, 1);
        List<Department> list2 = departmentService.listAllSon(wrapper);
        System.out.println("list = " + list2);
    }

    @Test
    void test7_Department_listAllSon_可以() {
        // 可以
        QueryWrapper<Object> wrapper = Wrappers.query()
                .eq("d.id", 1);
        List<Department> list2 = departmentService.listAllSon(wrapper);
        System.out.println("list = " + list2);
    }

}
