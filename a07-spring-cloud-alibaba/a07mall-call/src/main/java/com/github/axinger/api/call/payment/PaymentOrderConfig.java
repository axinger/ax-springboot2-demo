package com.github.axinger.api.call.payment;

import com.github.axinger.api.TestServer;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Map;

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

            Map<String, Collection<String>> headers = template.headers();
            Collection<String> authorization = headers.get("Authorization");
            System.out.println("authorization = " + authorization);
            template.removeHeader("Authorization");

            for (String string : authorization) {
                if (!string.startsWith("Bearer ")) {
                    template.header("Authorization", "Bearer " + string);
                }
            }


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
