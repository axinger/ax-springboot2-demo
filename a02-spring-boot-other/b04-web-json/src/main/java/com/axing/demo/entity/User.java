package com.axing.demo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private String name;
    private Integer age;

    private LocalDateTime localDateTime = LocalDateTime.now();
}
