package com.github.axinger.order.api;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class PaymentRefundConfig {


    private final String contextPath = "/payment";

    @Value("${server-name.payment-service}")
    private String paymentService;

    @Autowired
    private TestServer testServer;

    @Bean
    public RequestInterceptor paymentRefundInterceptor() {
        return template -> {
            System.out.println("paymentService = " + paymentService);

            System.out.println("testServer = " + testServer);


//            template.header("Authorization", "Bearer " + "Refund");
//            String url = template.url();
//            String originalPath = template.path();
//            template.uri(contextPath+url + originalPath);

        };
    }

    @Bean
    Logger.Level paymentRefundLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
