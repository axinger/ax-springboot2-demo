package com.axing.demo.service.impl;

import com.axing.demo.domain.Department;
import com.axing.demo.mapper.DepartmentMapper;
import com.axing.demo.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xing
 * @description 针对表【department】的数据库操作Service实现
 * @createDate 2022-04-24 09:31:32
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
        implements DepartmentService {

}




