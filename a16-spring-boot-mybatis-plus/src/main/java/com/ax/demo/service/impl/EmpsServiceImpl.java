package com.ax.demo.service.impl;

import com.ax.demo.entity.Emps;
import com.ax.demo.mapper.EmpsMapper;
import com.ax.demo.service.EmpsService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xing
 * @description 针对表【emps】的数据库操作Service实现
 * @createDate 2022-04-24 09:31:37
 */
@Service
public class EmpsServiceImpl extends ServiceImpl<EmpsMapper, Emps>
        implements EmpsService {
    @Override
    public Emps getEmployeeandDepartment(Integer id) {
        return baseMapper.getEmployeeandDepartment(id);
    }


    @Override
    public IPage<Emps> getPage(Page<Emps> page, Wrapper<Emps> queryWrapper) {
        return baseMapper.findByPage(page, queryWrapper);
    }
}




