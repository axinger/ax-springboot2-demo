package com.axing.demo.service;

import com.axing.demo.domain.Department;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xing
 * @description 针对表【department】的数据库操作Service
 * @createDate 2022-12-17 19:55:51
 */
public interface DepartmentService extends IService<Department> {

    List<Department> departByEmployeeList(Wrapper wrapper);

    List<Department> listLeft(Wrapper wrapper);

    List<Department> listLeftSon(Wrapper wrapper);

    List<Department> listAllSon(Wrapper wrapper);


}
