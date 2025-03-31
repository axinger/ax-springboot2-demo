package com.github.axinger.api.payment;

import com.github.axinger.api.TestServer;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class PaymentOrderConfig {

    private final String contextPath = "/payment";

    @Value("${server-name.payment-service}")
    private String paymentService;


    @Autowired
    private TestServer testServer;

    @Bean
    public RequestInterceptor paymentOrderInterceptor() {
        return template -> {
            System.out.println("paymentService = " + paymentService);
            System.out.println("testServer = " + testServer);

            template.header("Authorization", "Bearer " + "Order");
//            String url = template.url();
//            String originalPath = template.path();
//            template.uri(contextPath + url + originalPath);
        };
    }

    @Bean
    Logger.Level paymentOrderLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
