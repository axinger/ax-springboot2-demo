package com.axing.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    //姓名
    String name;
    //生产日期
    Date date;
    //产量
    BigDecimal yield;
}