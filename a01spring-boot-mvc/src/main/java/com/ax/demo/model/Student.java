package com.ax.demo.model;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Student extends Person {

    public Student(String name, Integer age) {
        super(name, age);
    }

    public Student() {
    }
}


