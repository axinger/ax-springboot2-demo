package com.ax.a22.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ax.a22.domain.TStudent;
import com.ax.a22.service.TStudentService;
import com.ax.a22.mapper.TStudentMapper;
import org.springframework.stereotype.Service;

/**
* @author xing
* @description 针对表【t_student(学生信息)】的数据库操作Service实现
* @createDate 2022-01-24 00:22:24
*/
@Service
public class TStudentServiceImpl extends ServiceImpl<TStudentMapper, TStudent>
    implements TStudentService{

}




