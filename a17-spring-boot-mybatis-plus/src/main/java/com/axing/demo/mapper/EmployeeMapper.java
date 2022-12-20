package com.axing.demo.mapper;

import com.axing.demo.domain.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xing
 * @description 针对表【employee】的数据库操作Mapper
 * @createDate 2022-12-17 20:22:09
 * @Entity com.axing.demo.domain.Employee
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 用来一对多的,条件查询多个
     *
     * @param deptId
     * @return
     */
    @Select("SELECT * FROM employee WHERE dept_id=#{deptId}")
    List<Employee> selectByDeptId(Integer deptId);
}




