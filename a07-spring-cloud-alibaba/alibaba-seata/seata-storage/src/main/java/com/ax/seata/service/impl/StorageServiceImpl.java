package com.ax.seata.service.impl;

import com.ax.seata.dao.TStorageDao;
import com.ax.seata.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName StorageServiceImpl.java
 * @Description TODO
 * @createTime 2021年12月19日 03:03:00
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Autowired
    private TStorageDao storageDao;

    @Override
    public int decrease(Long productId, Integer count) {
        return storageDao.decrease(productId,count);
    }
}
