package com.axing.service.impl;

import com.axing.demo.entity.Student;
import com.axing.demo.mapper.StudentMapper;
import com.axing.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xing
 * @description 针对表【t_student】的数据库操作Service实现
 * @createDate 2022-08-18 09:57:58
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
        implements StudentService {

}




