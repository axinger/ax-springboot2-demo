package com.ax.test.java;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

    @Test
    public void test1() {

        System.out.println("System.currentTimeMillis() = " + System.currentTimeMillis());

        /**
         * java.util.Date 类
         * 1. 两个构造器
         * 2.两个方法 toString,getTime()
         * */

        Date date = new Date();
        System.out.println("date = " + date);
        System.out.println("date.getTime() = " + date.getTime());
        System.out.println("new Date(date.getTime()) = " + new Date(date.getTime()));

    }

    @Test
    public void test2() {
        // 和数据库交互时候使用
        java.sql.Date date = new java.sql.Date(1638016018479L);

        System.out.println("date = " + date);

    }

    @Test
    public void test3() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println("format.format(new Date()) = " + format.format(new Date()));


        Date date = format.parse("2021-11-27 20:59:25:545");
        System.out.println("date = " + date);
    }

    @Test
    public void test4() {

        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar.getClass() = " + calendar.getClass());
        System.out.println("calendar.get = " + calendar.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, 27);
        System.out.println("calendar.get = " + calendar.get(Calendar.DAY_OF_MONTH));
        calendar.add(Calendar.DAY_OF_MONTH, -5);
        System.out.println("calendar.get = " + calendar.get(Calendar.DAY_OF_MONTH));

        Date date = calendar.getTime();
        System.out.println("date = " + date);

        calendar.setTime(date);


    }

    @Test
    public void test1_1() {

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
    public void test2_2() {
        Instant instant = Instant.now();
        System.out.println("instant = " + instant);// 零时区
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println("offsetDateTime = " + offsetDateTime);

        long milli = instant.toEpochMilli();// 自1970年毫秒数
        System.out.println("milli = " + milli);
    }

    @Test
    public void test3_3() {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        System.out.println("formatter = " + formatter);

        String format = formatter.format(LocalDateTime.now());
        System.out.println("format = " + format);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);

        String s1 = formatter1.format(LocalDateTime.now());
        System.out.println("s1 = " + s1);

    }
}
