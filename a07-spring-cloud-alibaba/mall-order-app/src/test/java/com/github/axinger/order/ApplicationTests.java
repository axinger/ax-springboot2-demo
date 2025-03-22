package com.github.axinger.order;

import com.github.axinger.order.domain.OrderPersonEntity;
import com.github.axinger.order.service.OrderPersonService;
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
