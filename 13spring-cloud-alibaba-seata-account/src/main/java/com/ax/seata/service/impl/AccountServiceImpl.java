package com.ax.seata.service.impl;

import com.ax.seata.dao.TAccountDao;
import com.ax.seata.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName StorageServiceImpl.java
 * @Description TODO
 * @createTime 2021年12月19日 03:03:00
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private TAccountDao accountDao;

    @Override
    public int decrease(Long userId, BigDecimal money) {
        return accountDao.decrease(userId, money);
    }
}
