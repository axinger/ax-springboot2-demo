package com.ax.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xing
 */
@RestController
@Slf4j
public class OrderController {


    @Value("${server.port}")
    private String port;

    @Resource
    private RestTemplate restTemplate;


    @Value("${server-url.nacos-payment-service}")
    private String paymentURL;


    @GetMapping(value = "/order/nacos/{id}")
    public Object order(@PathVariable("id") Integer id) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("order_id", id);
        map.put("order_name", "订单");
        map.put("order_port", port);

        String url = paymentURL + "/payment/nacos/" + id;
        System.out.println("url = " + url);

        Map<String, Object> map1 = restTemplate.getForObject(paymentURL + "/payment/nacos/" + id, Map.class);
        map.putAll(map1);
        return map;

    }


}
