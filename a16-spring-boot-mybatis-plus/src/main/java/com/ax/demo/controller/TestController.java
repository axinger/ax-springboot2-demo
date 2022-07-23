package com.ax.demo.controller;

import com.ax.demo.annotation.DS_DB1;
import com.ax.demo.annotation.DS_MASTER;
import com.ax.demo.db2.service.UserRoleService;
import com.ax.demo.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName TestController.java
 * @description TODO
 * @createTime 2022年07月27日 10:56:00
 */
@RestController
@Tag(name = "TestController", description = "测试控制器")
public class TestController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/student")
    @DS_MASTER
    public List studentService() {
        return studentService.list();
    }


    /**
     * 动态数据源: 方法中使用ds注解 ,
     */
    @GetMapping("/userRole")
//    @DS("db_ax_demo")
    @DS_DB1
    public List userRoleService() {
        return userRoleService.list();
    }
}
