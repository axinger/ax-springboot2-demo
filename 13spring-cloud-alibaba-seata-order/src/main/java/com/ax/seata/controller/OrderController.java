package com.ax.seata.controller;

import com.ax.seata.domain.TOrder;
import com.ax.seata.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName OrderController.java
 * @Description TODO
 * @createTime 2021年12月19日 00:52:00
 */

@RestController
public class OrderController {

    @Autowired
    TOrderService orderService;


    @GetMapping("/test")
    public Object test() {
        return "test-OrderController";
    }


    @GetMapping("/addOrder")
    public Object add(@RequestParam(value = "productId") Long productId) {
        final TOrder order = TOrder.builder()
                .userId(1L)
                .productId(1L)
                .count(10)
                .money(BigDecimal.valueOf(50.00))
                .status(0)
                .build();
        return orderService.create(order);
    }
}
