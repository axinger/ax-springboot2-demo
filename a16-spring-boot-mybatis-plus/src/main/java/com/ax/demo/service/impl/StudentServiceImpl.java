package com.ax.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ax.demo.entity.Student;
import com.ax.demo.service.StudentService;
import com.ax.demo.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author xing
* @description 针对表【t_student】的数据库操作Service实现
* @createDate 2022-04-24 09:26:21
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




