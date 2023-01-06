package com.axing.demo.db1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.axing.demo.db1.domain.Student;
import com.axing.demo.db1.service.StudentService;
import com.axing.demo.db1.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author xing
* @description 针对表【t_student】的数据库操作Service实现
* @createDate 2023-01-06 09:27:38
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




