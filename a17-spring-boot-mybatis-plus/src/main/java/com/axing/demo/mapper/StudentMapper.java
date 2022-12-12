package com.axing.demo.mapper;

import com.axing.demo.domain.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author xing
* @description 针对表【t_student】的数据库操作Mapper
* @createDate 2022-12-12 17:08:34
* @Entity com.axing.demo.domain.Student
*/@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}




