package com.github.axinger.controller;

import com.github.axinger.api.order.api.PaymentOrderApi2;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xing
 */
@RestController
@Slf4j
@RequestMapping("/order2")
public class OrderController2 {


    @Autowired
    private PaymentOrderApi2 paymentOrderApi;

    @Operation(summary = "订单系统")
    @GetMapping(value = "/list")
    public Object order1(String id) {
        return paymentOrderApi.count(id);
    }


}
