package com.axing.demo;

import cn.hutool.core.date.LocalDateTimeUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName DateTest.java
 * @description TODO
 * @createTime 2022年07月28日 13:56:00
 */

public class DateTest {


    @Test
    void test_before() {

        LocalDateTime now = LocalDateTime.now();
        final LocalDateTime beginOfDay = LocalDateTimeUtil.beginOfDay(LocalDateTime.now());

        final LocalDateTime endOfDay = LocalDateTimeUtil.endOfDay(LocalDateTime.now());


        System.out.println("localDateTime.isBefore(LocalDateTime.now()) = " + beginOfDay.isBefore(LocalDateTime.now()));

        System.out.println(LocalDateTime.now().isBefore(endOfDay));
        System.out.println(now.isBefore(now));
        System.out.println(now.isAfter(now));
        System.out.println(now.equals(now));

        System.out.println(now.compareTo(now));
        System.out.println(beginOfDay.compareTo(endOfDay));
        System.out.println(endOfDay.compareTo(beginOfDay));

        System.out.println(now.compareTo(now) < 1);
        System.out.println(beginOfDay.compareTo(endOfDay) < 1);
        System.out.println(endOfDay.compareTo(beginOfDay) < 1);
    }

    @Test
    void test_withDayOfYear() {
        final LocalDateTime localDateTime = LocalDateTimeUtil.beginOfDay(LocalDateTime.now().withDayOfYear(1));
        System.out.println("localDateTime = " + localDateTime);

        System.out.println(LocalDateTime.now().withDayOfYear(1));

    }

    @Test
    void test_last_month() {
        // 上个月最后一天结束时间
        final LocalDateTime localDateTime = LocalDateTimeUtil.beginOfDay(LocalDateTime.now().withDayOfMonth(1));

        LocalDateTime of = LocalDateTimeUtil.offset(localDateTime, -1, ChronoUnit.MICROS);
        System.out.println("of = " + of);

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
