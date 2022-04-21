package com.ax.demo.mapper;

import com.ax.demo.entity.Emps;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.*;

/**
* @author xing
* @description 针对表【emps】的数据库操作Mapper
* @createDate 2022-04-10 00:58:13
* @Entity com.ax.demo.domain.Emps
*/
public interface EmpsMapper extends BaseMapper<Emps> {


    // 关键点：用注解版的mybatis实现多对一的查询，其他多对多，一对多也是一样的方法。
    // 这里将emp中的公寓id（deptid）作为参数，然后用com.refactor.demo.Dao.DepartmentMapper.selectById方法进行查询。
    // com.refactor.demo.Dao.DepartmentMapper.selectById是mybatisplus自动定义好，很方便。
//    @Results(id="empdept", value =
    @Results( value =
    @Result(column = "deptid",property = "department",
            one=@One(select = "com.ax.demo.mapper.DepartmentMapper.selectById"))
    )
    @Select("select * from emps where id=#{id}")
    Emps getEmployeeandDepartment(Integer id);


    /**
     * mybatis-plus多表查询
     * @param page
     * @param wrapper
     * @return
     */

    //参数加上@Param(Constants.WRAPPER),xml里加上${ew.customSqlSegment}可以实现复杂条件检索查询
    IPage<Emps> findByPage(IPage<Emps> page, @Param(Constants.WRAPPER) Wrapper<Emps> wrapper);

}




