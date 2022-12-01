package com.axing.demo;

import cn.hutool.core.util.NumberUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName NumTests.java
 * @description TODO
 * @createTime 2022年07月29日 01:12:00
 */

public class NumTests {

    @Test
    void test_1() {
        final String s = NumberUtil.decimalFormat("#.##%", 0.123456);
        System.out.println("s = " + s);
    }

    @Test
    void test_2() {
        final String s = NumberUtil.decimalFormat("#%", 0.123456);
        System.out.println("s = " + s);
    }

    @Test
    void test_3() {
        final String s = NumberUtil.decimalFormat("#", 2.123456);
        System.out.println("s = " + s);
    }

    @Test
    void test_4() {
        final String s = NumberUtil.decimalFormat("#", new BigDecimal("2.123456"));
        System.out.println("s = " + s);
    }

    @Test
    void test_5() {
        final String s = NumberUtil.decimalFormat("#%", 1);
        System.out.println("s = " + s);
    }
}
