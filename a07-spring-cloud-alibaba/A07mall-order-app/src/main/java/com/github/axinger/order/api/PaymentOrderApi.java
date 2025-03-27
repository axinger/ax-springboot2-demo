package com.github.axinger.order.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

// @FeignClient configuration 指定的配置类不要加@Configuration，如果加了@Configuration注解，那这个配置类就是一个全局配置类。
@Component
@FeignClient(value = "${server-name.payment-service}/payment",
        path = "/order",
        contextId = "PaymentOrderApi",
        configuration = PaymentOrderConfig.class)
@Import(PaymentOrderConfig.class)
public interface PaymentOrderApi {

    @GetMapping(value = "/count/{id}")
    Map<String, Object> count(@PathVariable("id") Integer id);
}
