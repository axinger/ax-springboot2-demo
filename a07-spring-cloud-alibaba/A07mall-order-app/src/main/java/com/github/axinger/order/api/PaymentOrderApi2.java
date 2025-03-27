package com.github.axinger.order.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Component
@FeignClient(
        value = "PaymentOrderApi2",
        contextId = "PaymentOrderApi2",
        url = "http://127.0.0.1:10708/payment/refund/count2",
        configuration = PaymentOrderConfig.class)
@Import(PaymentOrderConfig.class)
public interface PaymentOrderApi2 {

    @GetMapping
//    @GetMapping("http://127.0.0.1:10708/payment/refund/count2")
    Map<String, Object> count(@RequestParam("id") String id);
}
