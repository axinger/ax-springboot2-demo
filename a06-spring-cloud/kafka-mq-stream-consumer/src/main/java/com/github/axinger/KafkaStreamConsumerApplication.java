package com.github.axinger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaStreamConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaStreamConsumerApplication.class, args);
    }

}
