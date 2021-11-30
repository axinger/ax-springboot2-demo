package com.ax.demo_java;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
}
