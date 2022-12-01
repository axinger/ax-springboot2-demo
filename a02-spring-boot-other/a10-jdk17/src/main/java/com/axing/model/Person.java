package com.axing.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {

    private String name;

    private int age;

    private static final long serialVersionUID = 1L;
}
