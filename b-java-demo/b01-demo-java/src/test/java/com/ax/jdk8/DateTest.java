package com.ax.jdk8;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class DateTest {
    @Test
    public void test1() {

        LocalDate localDate = LocalDate.now();
        System.out.println("localDate = " + localDate);


        LocalTime localTime = LocalTime.now();
        System.out.println("localTime = " + localTime);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);

        LocalDateTime localDateTime1 = LocalDateTime.of(2021, 11, 13, 12, 20, 01);
        System.out.println("localDateTime1 = " + localDateTime1);


        System.out.println("getDayOfMonth = " + localDateTime1.getDayOfMonth());
        System.out.println("getMonth = " + localDateTime1.getMonth());
        System.out.println("getMonthValue = " + localDateTime1.getMonthValue());
        System.out.println("getDayOfWeek = " + localDateTime1.getDayOfWeek());
        System.out.println("withDayOfMonth = " + localDateTime1.withDayOfMonth(12));


    }

    @Test
    public void test2() {
        Instant instant = Instant.now();
        System.out.println("instant = " + instant);// 零时区
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println("offsetDateTime = " + offsetDateTime);

        long milli = instant.toEpochMilli();// 自1970年毫秒数
        System.out.println("milli = " + milli);
    }

    @Test
    public void test3() {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        System.out.println("formatter = " + formatter);

        String format = formatter.format(LocalDateTime.now());
        System.out.println("format = " + format);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);

        String s1 = formatter1.format(LocalDateTime.now());
        System.out.println("s1 = " + s1);

    }
}
