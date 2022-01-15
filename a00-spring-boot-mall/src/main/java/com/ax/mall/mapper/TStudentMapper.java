package com.ax.mall.mapper;

import com.ax.mall.entity.TStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
* @author xing
* @description 针对表【t_student(学生信息)】的数据库操作Mapper
* @createDate 2021-12-21 20:09:19
* @Entity mapper.entity.TStudent
*/
@Mapper
public interface TStudentMapper extends BaseMapper<TStudent> {

}




