package com.axing.demo.service.impl;

import com.axing.demo.domain.Student;
import com.axing.demo.mapper.StudentMapper;
import com.axing.demo.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author xing
 * @description 针对表【t_student】的数据库操作Service实现
 * @createDate 2023-01-06 11:35:44
 */
@Service
@CacheConfig(cacheNames = "st")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
        implements StudentService {

    // @Cacheable(key = "#id")
    // @Override
    // public Student getById(Serializable id) {
    //     return StudentService.super.getById(id);
    // }
    //
    // @Override
    // public List<Student> list() {
    //     return StudentService.super.list();
    // }
}




