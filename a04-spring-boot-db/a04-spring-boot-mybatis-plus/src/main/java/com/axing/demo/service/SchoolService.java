package com.axing.demo.service;

import com.axing.demo.domain.School;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xing
 * @description 针对表【t_school】的数据库操作Service
 * @createDate 2022-12-17 19:13:05
 */
public interface SchoolService extends IService<School> {

    List<School> schoolList(@Param(Constants.WRAPPER) Wrapper<School> queryWrapper);

    List<School> schoolList();
}
