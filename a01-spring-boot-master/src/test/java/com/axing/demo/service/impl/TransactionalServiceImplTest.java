package com.axing.demo.service.impl;

import com.axing.demo.service.TransactionalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionalServiceImplTest {

    @Autowired
    private TransactionalService transactionalService;


    @Test
    void insetSuccess() {
        transactionalService.insetSuccess(false);
    }

    @Test
    void insetSuccess_2() throws Exception {
        transactionalService.insetSuccess(true);
    }

    @Test
    void insetSuccess2() {
        transactionalService.insetSuccess2(false);
    }

    @Test
    void insetSuccess2_2() {
        transactionalService.insetSuccess2(true);
    }
}
