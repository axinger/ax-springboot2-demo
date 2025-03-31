package com.github.axinger.api.order;

import com.github.axinger.domain.OrderPersonEntity;
import com.github.axinger.service.OrderPersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private OrderPersonService orderPersonService;

    @Test
    void test1() {
        List<OrderPersonEntity> list = orderPersonService.list();
        System.out.println("list = " + list);
    }

}
