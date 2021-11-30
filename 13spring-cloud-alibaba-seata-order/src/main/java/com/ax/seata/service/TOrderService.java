package com.ax.seata.service;

import com.ax.seata.domain.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author xing
* @description 针对表【t_order】的数据库操作Service
* @createDate 2021-12-19 00:46:12
*/
public interface TOrderService extends IService<TOrder> {

    /**
     * 创建订单
     * @param order 订单
     * @return 是否成功
     *
     * */
    boolean create(TOrder order);
}
