package com.github.axinger.payment.controller;


import brave.Tracer;
import brave.propagation.TraceContext;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xing
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String port;


    @Autowired
    private Tracer tracer;


    @Operation(summary = "支付系统,订单")
    @GetMapping(value = "/count/{id}")
    public Map<String, Object> payment(@PathVariable("id") String id) {
        System.out.println("payment_id = " + id);
        Map<String, Object> map = new HashMap<>(16);
        map.put("payment_id", id);
        map.put("payment_name", "支付系统");
        map.put("payment_port", port);

        TraceContext context = tracer.currentSpan().start().context();

        map.put("parentId", context.parentId());
        map.put("traceId", context.traceId());
        map.put("spanId", context.spanId());

        map.put("traceIdHigh", context.traceIdHigh());
        map.put("traceIdString", context.traceIdString());
        map.put("isLocalRoot", context.isLocalRoot());
        return map;
    }

}
