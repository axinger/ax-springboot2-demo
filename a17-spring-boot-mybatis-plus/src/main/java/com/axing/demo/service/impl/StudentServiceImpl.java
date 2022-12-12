package com.axing.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.axing.demo.domain.Student;
import com.axing.demo.service.StudentService;
import com.axing.demo.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author xing
* @description 针对表【t_student】的数据库操作Service实现
* @createDate 2022-12-12 17:08:34
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




