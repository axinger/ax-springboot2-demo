package com.axing.demo.controller;

import com.axing.db.service.DepartmentService;
import com.axing.demo.annotation.DS_DB1;
import com.axing.demo.annotation.DS_MASTER;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    private DepartmentService departmentService;

    @GetMapping("/student")
    @DS_MASTER
    @ResponseBody
    public List studentService() {
        return null;
    }

    @GetMapping("/error")
    public List error() throws Exception {
        throw new Exception("1231");
    }

    /**
     * 动态数据源: 方法中使用ds注解 ,
     */
    @GetMapping("/userRole")
//    @DS("db_ax_demo")
    @DS_DB1
    public List userRoleService() {
        return null;
    }

}
