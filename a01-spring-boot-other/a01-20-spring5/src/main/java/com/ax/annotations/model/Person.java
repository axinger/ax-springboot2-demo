package com.ax.annotations.model;

import org.springframework.stereotype.Repository;

@Repository
public class Person {

    private String name;

    public void add() {
        System.out.println("add........Person");
    }
}
