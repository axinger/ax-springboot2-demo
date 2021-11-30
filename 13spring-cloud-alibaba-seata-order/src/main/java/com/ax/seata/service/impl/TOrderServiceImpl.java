package com.ax.seata.service.impl;

import com.ax.seata.domain.TOrder;
import com.ax.seata.mapper.TOrderMapper;
import com.ax.seata.service.AccountService;
import com.ax.seata.service.StorageService;
import com.ax.seata.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xing
 * @description 针对表【t_order】的数据库操作Service实现
 * @createDate 2021-12-19 00:46:12
 */
@Service
@Slf4j
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {


    @Autowired
    private StorageService storageService;

    @Autowired
    private AccountService accountService;


    @Override
    /**
     * name 唯一性就行,不是conf文件的
     * */
    @GlobalTransactional(name = "gt_order_create",rollbackFor = Exception.class)
    public boolean create(TOrder order) {

        log.info("==========新建订单");
        baseMapper.create(order);

        log.info("==========调用库存微服务,减库存");
        storageService.decrease(order.getProductId(), order.getCount());

        log.info("==========调用账户微服务,减余额");
        accountService.decrease(order.getUserId(), order.getMoney());

        log.info("==========修改订单状态");
        baseMapper.updateStatus(order.getUserId(), 0);

        log.info("==========下订单结束");

        return true;
    }
}
