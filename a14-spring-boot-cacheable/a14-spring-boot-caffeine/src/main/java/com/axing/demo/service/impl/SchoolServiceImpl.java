package com.axing.demo.service.impl;

import com.axing.demo.domain.School;
import com.axing.demo.mapper.SchoolMapper;
import com.axing.demo.service.SchoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xing
 * @description 针对表【t_school】的数据库操作Service实现
 * @createDate 2023-01-06 12:08:40
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School>
        implements SchoolService {

}




