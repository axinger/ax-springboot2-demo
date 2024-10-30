package com.github.axinger;

import org.junit.jupiter.api.Test;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;


/**
 * @author xing
 * @version 1.0.0
 * @ClassName NumberTest.java
 * @description TODO
 * @createTime 2022年05月20日 22:00:00
 */


public class NumberTest {

    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,###")
    private final Number total = 10000;

    @NumberFormat(style = NumberFormat.Style.PERCENT)
    private final double discount = 10000;

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private final double money = 10000;
    @NumberFormat(pattern = "#,###")
    private final Integer salary = 1000;

    @Test
    void test1() {

        System.out.println("total = " + total);
        System.out.println("discount = " + discount);
        System.out.println("money = " + money);
        System.out.println("salary = " + salary);

        FormatDemo formatDemo = new FormatDemo();// 格式化对象的类
        formatDemo.format1("###,###.###", 123468.4986);
        formatDemo.format1("000,000.000", 18956.6615);
        formatDemo.format1("###,###.###￥", 156498.948165);
        formatDemo.format1("000,000.000￥", 49861.49861);
        formatDemo.format1("##.###%", 0.1656498);
        formatDemo.format1("00.###%", 0.0156165);
        formatDemo.format1("###,###", 0.1684489);
    }

    @Test
    void test2() {
//        float num= (float)6/3;
//        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
//        String s = df.format(num);//返回的是String类型
//        System.out.println("s = " + s);


        final DecimalFormat decimalFormat = new DecimalFormat("0.0000");

        System.out.println(decimalFormat.format(20.0 / 2));

        final DecimalFormat df2 = new DecimalFormat("0.00");
        System.out.println("df2 = " + df2.format(((float) 1 / 8) * 100));

        BigDecimal percentage = BigDecimal.valueOf(1)
                .divide(BigDecimal.valueOf(8), 4, RoundingMode.FLOOR)
                .multiply(BigDecimal.valueOf(100));
        System.out.println("percentage = " + percentage);


    }

    @Test
    void test3() {

        BigDecimal bigDecimal = BigDecimal.valueOf(2.1234);

        System.out.println("bigDecimal = " + bigDecimal);

        System.out.printf("%.1f%n", bigDecimal);

    }

    @Test
    void test4() {

        System.out.println("m*(2的n次方）====================");
        // m<<n: 等于m*(2的n次方）
        System.out.println(2 << 2); // 8 2*2的2次方
        System.out.println(2 << 3); // 16
        System.out.println(2 << 4); // 32
        System.out.println(2 << 5); // 64

        System.out.println(3 << 2); // 12 3*2的2次方
        System.out.println(3 << 3); // 24 3*2的3次方

        System.out.println(1 << 3); // 8 1*2的3次方


        System.out.println("m的n次方====================");
        // Math.pow(m，n)，表示m的n次方
        System.out.println(Math.pow(2, 6));
        System.out.println(Math.pow(2.1, 6));

        System.out.println("指定进制====================");
        // 源的指定进制,转换为十进制结果
        System.out.println(Integer.valueOf("10", 2));
        System.out.println(Integer.valueOf("10", 8));
        System.out.println(Integer.valueOf("10", 10));
        System.out.println(Integer.valueOf("10", 16));
        System.out.println(Integer.valueOf("01", 16));
        System.out.println(Integer.valueOf("0a", 16));
    }

}
