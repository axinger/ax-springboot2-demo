package com.axing.demo.service.impl;

import com.axing.demo.entity.Student;
import com.axing.demo.mapper.StudentMapper;
import com.axing.demo.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xing
 * @description 针对表【t_student】的数据库操作Service实现
 * @createDate 2022-04-24 09:26:21
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
        implements StudentService {

}




