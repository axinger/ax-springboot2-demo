package com.axing.demo;

import com.axing.demo.entity.Pig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebJsonAppTest {
    @Autowired
    private Pig pig;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void pig() {

        System.out.println("new Pig() = " + new Pig());
        System.out.println("pig = " + pig);

        System.out.println("objectMapper = " + objectMapper);
    }
}