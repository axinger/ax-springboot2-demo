package com.github.axinger.order.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Component
@FeignClient(value = "${server-name.payment-service}",
        path = "/payment/refund",
        contextId = "PaymentRefundApi",
        configuration = PaymentRefundConfig.class)
@Import(PaymentRefundConfig.class)
public interface PaymentRefundApi {

    @GetMapping(value = "/count/{id}")
    Map<String, Object> count(@PathVariable("id") Integer id);
}
