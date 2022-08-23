package com.axing.demo;

import cn.hutool.core.date.LocalDateTimeUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName DateTest.java
 * @description TODO
 * @createTime 2022年07月28日 13:56:00
 */

public class DateTest {


    @Test
    void test_withDayOfYear() {
        final LocalDateTime localDateTime = LocalDateTimeUtil.beginOfDay(LocalDateTime.now().withDayOfYear(1));
        System.out.println("localDateTime = " + localDateTime);

        System.out.println(LocalDateTime.now().withDayOfYear(1));

    }


    @Test
    void test_1() {
        final LocalDateTime localDateTime = LocalDateTimeUtil.beginOfDay(LocalDateTime.now().withDayOfMonth(1));
        System.out.println("localDateTime = " + localDateTime);

    }

    @Test
    void test_2() {
        final LocalDateTime localDateTime = LocalDateTimeUtil.beginOfDay(LocalDateTime.now().withMonth(1).withDayOfMonth(1));
        System.out.println("localDateTime = " + localDateTime);

    }
}
