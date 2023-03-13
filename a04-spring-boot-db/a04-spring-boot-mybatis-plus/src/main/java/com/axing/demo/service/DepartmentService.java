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

    List<Department> departByEmployeeList(Wrapper<Department> wrapper);

    List<Department> listLeft(Wrapper<Department> wrapper);

    /**
     * select 返回结果,再包装一个select
     * @param wrapper
     * @return
     */
    List<Department> listLeftSon(Wrapper<Department> wrapper);

    /**
     * 不支持 lambda 方法引用
     * 使用where 左边会丢数据,不推荐
     * @param wrapper
     * @return
     */
    List<Department> listWhere(Wrapper<Department> wrapper);


}
