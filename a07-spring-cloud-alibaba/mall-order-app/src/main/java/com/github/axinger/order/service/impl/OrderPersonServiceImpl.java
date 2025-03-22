package com.github.axinger.order.service.impl;

import com.baomidou.dynamic.datasource.annotation.Master;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.axinger.order.domain.OrderPersonEntity;
import com.github.axinger.order.service.OrderPersonService;
import com.github.axinger.order.mapper.OrderPersonMapper;
import org.springframework.stereotype.Service;

/**
* @author xing
* @description 针对表【order_person】的数据库操作Service实现
* @createDate 2025-03-21 22:56:41
*/
@Service
@Master
public class OrderPersonServiceImpl extends ServiceImpl<OrderPersonMapper, OrderPersonEntity>
    implements OrderPersonService{

}




