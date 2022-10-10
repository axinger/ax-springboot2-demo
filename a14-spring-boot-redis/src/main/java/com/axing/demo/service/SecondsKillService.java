package com.axing.demo.service;

public interface SecondsKillService {

    boolean doSecKill(Integer userId, Integer prodId);


    /// 初始化库存
    void createProductCount(Integer prodId);


    Integer productCount(Integer prodId);


    boolean doSecKillOfRedissonLock(Integer userId, Integer prodId);


}
