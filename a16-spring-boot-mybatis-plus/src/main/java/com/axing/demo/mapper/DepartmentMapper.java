package com.axing.demo.mapper;

import com.axing.demo.entity.Department;
import com.axing.demo.vo.DepartmentVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author xing
 * @description 针对表【department】的数据库操作Mapper
 * @createDate 2022-04-24 09:31:32
 * @Entity com.ax.demo.entity.Department
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 动态查询
     */
    @Select("SELECT * " +
            " FROM department, emps " +
            " ${ew.customSqlSegment} ")
    IPage<DepartmentVo> find(IPage<DepartmentVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("SELECT * " +
            " FROM department " +
            " ${ew.customSqlSegment} ")
    List<Department> find2(@Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 一对多
     * listByDeptid 这个是非主键条件,所以需要手动写一个
     *
     * @return
     */
    @Results({
            @Result(
                    column = "dept_id", property = "empList",
                    many = @Many(select = "com.ax.demo.mapper.EmpsMapper.selectByDeptId")
                    // 用service 不行
//                    many = @Many(select = "com.ax.demo.service.EmpsService.findByDeptId")
            )
    })
    @Select("SELECT * FROM department LEFT JOIN emps ON (emps.dept_id = department.id)")
    List<Department> getDepartmentEmpsList();


    @Results({
            @Result(
                    column = "dept_id", property = "empList",
                    many = @Many(select = "com.ax.demo.mapper.EmpsMapper.selectByDeptId")
            )
    })
    @Select("SELECT * FROM department LEFT JOIN emps ON (emps.dept_id = department.id) "
            + "${ew.customSqlSegment}")
    List<Department> getDepartmentEmpsList2(@Param(Constants.WRAPPER) Wrapper wrapper);


    //参数加上@Param(Constants.WRAPPER),xml里加上${ew.customSqlSegment}可以实现复杂条件检索查询

}




