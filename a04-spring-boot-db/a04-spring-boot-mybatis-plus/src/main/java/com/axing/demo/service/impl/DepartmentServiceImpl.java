package com.axing.demo.service.impl;

import com.axing.demo.domain.Department;
import com.axing.demo.mapper.DepartmentMapper;
import com.axing.demo.service.DepartmentService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xing
 * @description 针对表【department】的数据库操作Service实现
 * @createDate 2022-12-17 19:55:51
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
        implements DepartmentService {

    @Override
    public List<Department> departByEmployeeList(Wrapper<Department> wrapper) {
        return this.baseMapper.departByEmployeeList(wrapper);
    }

    @Override
    public List<Department> listLeft(Wrapper<Department> wrapper) {
        return this.baseMapper.listLeft(wrapper);
    }

    @Override
    public List<Department> listLeftSon(Wrapper<Department> wrapper) {
        return this.baseMapper.listLeftSon(wrapper);
    }

    @Override
    public List<Department> listWhere(Wrapper<Department> wrapper) {
        return this.baseMapper.listWhere(wrapper);
    }
}




