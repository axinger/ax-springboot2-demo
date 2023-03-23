package com.axing.demo;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

public class DateTests {

    @Test
    void test1() {
        // LocalDateTime 转 DateTime extends Date
        Date date = DateUtil.date(LocalDateTime.now());
        System.out.println("date = " + date);
        System.out.println("new Date() = " + new Date());
    }
}
