package com.axing.demo.db2.service.impl;

import com.axing.demo.db2.domain.Department;
import com.axing.demo.db2.mapper.DepartmentMapper;
import com.axing.demo.db2.service.DepartmentService;
import com.baomidou.dynamic.datasource.annotation.DS;
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
@DS("db_ax_test")
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
        implements DepartmentService {

    @Override
    public List<Department> getDepartmentByEmployee(Wrapper wrapper) {
        return this.baseMapper.getDepartmentByEmployee(wrapper);
    }

    @Override
    public List<Department> listLeft(Wrapper wrapper) {
        return this.baseMapper.listLeft(wrapper);
    }

    @DS("db_ax_test")
    @Override
    public List<Department> listLeftEmployee(Wrapper wrapper) {
        this.list();
        return this.getBaseMapper().listLeftEmployee(wrapper);
    }

    @Override
    public List<Department> listAllSon(Wrapper wrapper) {
        return this.baseMapper.listAllSon(wrapper);
    }
}




