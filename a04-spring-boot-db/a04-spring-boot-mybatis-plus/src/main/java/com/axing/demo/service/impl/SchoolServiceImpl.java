package com.axing.demo.service.impl;

import com.axing.demo.domain.School;
import com.axing.demo.mapper.SchoolMapper;
import com.axing.demo.service.SchoolService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xing
 * @description 针对表【t_school】的数据库操作Service实现
 * @createDate 2022-12-17 19:13:05
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School>
        implements SchoolService {

    @Override
    public List<School> schoolList(@Param(Constants.WRAPPER) Wrapper<School> queryWrapper) {
        return this.baseMapper.schoolList(queryWrapper);
    }
}




