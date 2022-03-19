package com.ax.test.java;

import org.junit.jupiter.api.Test;

enum Week {
    ONE("一", 1),
    TWO("二", 2),
    ;

    private String name;
    private int index;

    Week(String name1, int index) {
        this.name = name1;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
};


public class EnumTest {


    @Test
    public void test1() {

        System.out.println(Week.ONE);
        System.out.println(Week.ONE.getName());
        for (Week value : Week.values()) {
            System.out.println(value);
        }

        System.out.println(Week.valueOf("ONE"));

    }
}
