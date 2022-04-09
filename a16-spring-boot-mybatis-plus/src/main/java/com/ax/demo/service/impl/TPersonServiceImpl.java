package com.ax.demo.service.impl;

import com.ax.demo.domain.TPerson;
import com.ax.demo.mapper.TPersonMapper;
import com.ax.demo.service.TPersonService;
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




