package com.ax.seata.mapper;

import com.ax.seata.domain.TOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author xing
* @description 针对表【t_order】的数据库操作Mapper
* @createDate 2021-12-19 00:46:12
* @Entity mapper.domain.TOrder
*/
@Mapper
public interface TOrderMapper extends BaseMapper<TOrder> {

   //创建订单
   void create(TOrder order);

   boolean updateStatus(Long userId,Integer status);

}
