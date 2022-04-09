package com.ax.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ax.demo.domain.Department;
import com.ax.demo.service.DepartmentService;
import com.ax.demo.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

/**
* @author xing
* @description 针对表【department】的数据库操作Service实现
* @createDate 2022-04-10 00:57:47
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{

}




