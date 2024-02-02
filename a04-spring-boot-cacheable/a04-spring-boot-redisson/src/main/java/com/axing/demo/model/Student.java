package com.axing.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable, Comparable<Student> {
    private Long id;
    private String name;
    private Integer age;

    @Override
    public int compareTo(Student obj) {
        return this.getId().compareTo(obj.getId());
    }
}
