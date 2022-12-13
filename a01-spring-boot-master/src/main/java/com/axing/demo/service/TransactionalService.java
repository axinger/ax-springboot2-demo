package com.axing.demo.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName TransactionalService.java
 * @Description TODO
 * @createTime 2021年12月28日 10:16:00
 */
public interface TransactionalService {

    Object insetSuccess(Boolean isError);

    Object insetSuccess2(Boolean isError);

    @Transactional(rollbackFor = Exception.class)
    Object insetSuccessWithTemplate(Boolean isError);
}


