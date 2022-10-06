package com.ax.a19.service.impl;

import com.ax.a19.domain.Student;
import com.ax.a19.mapper.StudentMapper;
import com.ax.a19.service.StudentService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xing
 * @description 针对表【t_student(学生信息)】的数据库操作Service实现
 * @createDate 2022-03-20 02:55:42
 */
@Service
@DS("master")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
        implements StudentService {

}




