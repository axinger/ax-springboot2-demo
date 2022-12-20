package com.axing.demo.mapper;

import com.axing.demo.domain.School;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xing
 * @description 针对表【t_school】的数据库操作Mapper
 * @createDate 2022-12-17 19:13:05
 * @Entity com.axing.demo.domain.School
 */
public interface SchoolMapper extends BaseMapper<School> {

    // @Results({
    //         @Result(
    //                 column = "dept_id", property = "empList",
    //                 many = @Many(select = "com.ax.demo.mapper.EmpsMapper.selectByDeptId")
    //         )
    // })
    // @Select("SELECT * FROM department LEFT JOIN emps ON (emps.dept_id = department.id) "
    //         + "${ew.customSqlSegment}")

    List<School> schoolList(@Param(Constants.WRAPPER) Wrapper<School> wrapper);

}




