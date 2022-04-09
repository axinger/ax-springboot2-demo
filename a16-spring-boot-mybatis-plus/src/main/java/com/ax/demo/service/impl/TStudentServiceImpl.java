package com.ax.demo.service.impl;

import com.ax.demo.domain.TStudent;
import com.ax.demo.mapper.TStudentMapper;
import com.ax.demo.service.TStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xing
 * @description 针对表【t_student(学生信息)】的数据库操作Service实现
 * @createDate 2022-03-20 01:07:25
 */
@Service
public class TStudentServiceImpl extends ServiceImpl<TStudentMapper, TStudent>
        implements TStudentService {

}




