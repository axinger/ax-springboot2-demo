package com.ax.a16.service.impl;

import com.ax.a16.domain.TPerson;
import com.ax.a16.mapper.TPersonMapper;
import com.ax.a16.service.TPersonService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xing
 * @description 针对表【t_person】的数据库操作Service实现
 * @createDate 2022-03-20 00:57:04
 */
@Service
@DS("slave_1")
public class TPersonServiceImpl extends ServiceImpl<TPersonMapper, TPerson>
        implements TPersonService {

}




