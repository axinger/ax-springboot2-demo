package com.ax.demo.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import xyz.ax.demo.entity.TAccount;
import xyz.ax.demo.service.TAccountService;

import java.math.BigDecimal;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName AccountServiceImpl.java
 * @Description TODO
 * @createTime 2021年12月18日 21:37:00
 */

@DubboService(version = "1.0.0", interfaceClass = TAccountService.class)
@Component
public class AccountServiceImpl implements TAccountService {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(TAccount record) {
        return 0;
    }

    @Override
    public int insertSelective(TAccount record) {
        return 0;
    }

    @Override
    public TAccount selectByPrimaryKey(Long id) {
        System.out.println("进入了AccountServiceImpl............." + id);

        final TAccount account = new TAccount();
        account.setId(1L);
        account.setUsed(BigDecimal.valueOf(1L));
        return account;
    }

    @Override
    public int updateByPrimaryKeySelective(TAccount record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TAccount record) {
        return 0;
    }
}
