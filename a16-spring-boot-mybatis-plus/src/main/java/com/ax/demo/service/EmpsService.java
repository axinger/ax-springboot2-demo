package com.ax.demo.service;

import com.ax.demo.entity.Emps;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author xing
 * @description 针对表【emps】的数据库操作Service
 * @createDate 2022-04-24 09:31:37
 */
public interface EmpsService extends IService<Emps> {

    Emps getEmployeeandDepartment(Integer id);

    IPage<Emps> getPage(Page<Emps> page, Wrapper<Emps> queryWrapper);
}
