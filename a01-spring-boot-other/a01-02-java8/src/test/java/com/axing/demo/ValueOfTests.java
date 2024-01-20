package com.axing.demo;

import org.junit.jupiter.api.Test;

public class ValueOfTests {

    //parse*方法比valueOf方法 效率高 。
    @Test
    void test1() {

//        long a = Integer.parseInt(""); //解析失败报错
//        System.out.println("parseLong = " + a);

        Integer integer = Integer.getInteger("a"); //解析失败为null
        System.out.println("integer = " + integer);

        System.out.println("======================");
    }

    @Test
    void test2() {
        // valueOf 调用就是parseLong
        Integer a = Integer.valueOf("null");
        System.out.println("valueOf = " + a);

    }
}
