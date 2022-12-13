package com.axing.demo.service.impl;

import com.axing.demo.entity.TStudent;
import com.axing.demo.mapper.TStudentMapper;
import com.axing.demo.service.TStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xing
 * @description 针对表【t_student(学生信息)】的数据库操作Service实现
 * @createDate 2021-12-21 20:09:19
 */
@Service
public class TStudentServiceImpl extends ServiceImpl<TStudentMapper, TStudent> implements TStudentService {

}




