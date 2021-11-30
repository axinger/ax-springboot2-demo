package com.ax.seata.service;


public interface StorageService {

    int decrease(Long productId,Integer count);
}
