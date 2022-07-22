package com.ax.demo.service;

import com.alibaba.fastjson2.JSON;
import com.ax.demo.entity.Department;
import com.ax.demo.mapper.DepartmentMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    DepartmentService departmentService;

    @Test
    public void test1() {

        final Department department = departmentService.getById("1");
        System.out.println("department = " + department);
    }


    @Test
    public void test2() {

        // 0 ,1 页是一样的
        final Page<Department> page = departmentService.lambdaQuery()
                .page(Page.of(1, 2));
        System.out.println("page = " + JSON.toJSONString(page));

        final List<Department> records = departmentService.lambdaQuery()
                .page(Page.of(1, 2))
                .getRecords();
        System.out.println("records = " + records);
    }

    @Test
    public void test3() {


        final DepartmentMapper baseMapper = (DepartmentMapper) departmentService.getBaseMapper();
        final List<Department> list = baseMapper.getDepartmentEmpsList();
        System.out.println("list = " + list);
    }

    @Test
    public void test4() {

//        final LambdaQueryWrapper<Department> wrapper = Wrappers.<Department>lambdaQuery()
//                .eq(Department::getName, "财务部");

        // id 冲突
        //  SELECT * FROM department LEFT JOIN emps ON (emps.dept_id = department.id) WHERE (name = ? AND department.id=2)
        final LambdaQueryWrapper<Department> wrapper = Wrappers.<Department>lambdaQuery()
                .eq(Department::getName, "销售部")
                .apply("department.id={0}", 2)

                //注入风险
//                .apply("department.id=2")
                ;

        final List<Department> list = ((DepartmentMapper) departmentService.getBaseMapper())
                .getDepartmentEmpsList2(wrapper);
        System.out.println("list = " + list);
    }

    @Test
    public void test5() {

        final LambdaQueryWrapper<Department> wrapper = Wrappers.<Department>lambdaQuery()
                .eq(Department::getId, 1);

        final List<Department> list = ((DepartmentMapper) departmentService.getBaseMapper())
                .find2(wrapper);
        System.out.println("list = " + list);
    }
}
