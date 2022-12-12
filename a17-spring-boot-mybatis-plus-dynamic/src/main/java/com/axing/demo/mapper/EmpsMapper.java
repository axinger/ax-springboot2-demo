package com.axing.demo.mapper;

import com.axing.demo.domain.Emps;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xing
 * @description 针对表【emps】的数据库操作Mapper
 * @createDate 2022-06-19 04:35:28
 * @Entity com.ax.demo.entity.Emps
 */
public interface EmpsMapper extends BaseMapper<Emps> {


    /**
     * 用来一对多的,条件查询多个
     *
     * @param deptId
     * @return
     */
    @Select("SELECT * FROM emps WHERE dept_id=#{deptId}")
    List<Emps> selectByDeptId(Integer deptId);
}




