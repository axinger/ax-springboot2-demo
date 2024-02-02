package com.axing.db.service;

import com.axing.db.domain.Employee;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xing
 * @description 针对表【employee】的数据库操作Service
 * @createDate 2022-12-17 20:22:09
 */
public interface EmployeeService extends IService<Employee> {

    List<Employee> listLeftSon(@Param(Constants.WRAPPER) Wrapper wrapper);
}
