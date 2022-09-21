package com.axing.demo;

import org.junit.jupiter.api.Test;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.text.DecimalFormat;


/**
 * @author xing
 * @version 1.0.0
 * @ClassName NumberTest.java
 * @description TODO
 * @createTime 2022年05月20日 22:00:00
 */
class FormatDemo {
    //此方法用于完成数字的格式化显示
    public void format1(String parrent, double value) {
        DecimalFormat decimalFormat = null;//声明对象
        decimalFormat = new DecimalFormat(parrent);//实例化并传入模板
        String string = decimalFormat.format(value);//格式化数字
        System.out.println("string = " + string);
    }

}

public class NumberTest {

    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,###")
    private Number total = 10000;

    @NumberFormat(style = NumberFormat.Style.PERCENT)
    private double discount = 10000;

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private double money = 10000;
    @NumberFormat(pattern = "#,###")
    private Integer salary = 1000;

    @Test
    void test1() {

        System.out.println("total = " + String.valueOf(total));
        System.out.println("discount = " + discount);
        System.out.println("money = " + money);
        System.out.println("salary = " + salary);

        FormatDemo formatDemo = new FormatDemo();//格式化对象的类
        formatDemo.format1("###,###.###", 123468.4986);
        formatDemo.format1("000,000.000", 18956.6615);
        formatDemo.format1("###,###.###￥", 156498.948165);
        formatDemo.format1("000,000.000￥", 49861.49861);
        formatDemo.format1("##.###%", 0.1656498);
        formatDemo.format1("00.###%", 0.0156165);
        formatDemo.format1("###,###", 0.1684489);
    }

    @Test
    void tet1() {
//        float num= (float)6/3;
//        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
//        String s = df.format(num);//返回的是String类型
//        System.out.println("s = " + s);


        final DecimalFormat decimalFormat = new DecimalFormat("0.0000");

        System.out.println(decimalFormat.format(20.0 / 2));

        final DecimalFormat df2 = new DecimalFormat("0.00");
        System.out.println("df2 = " + df2.format(((float) 1 / 8) * 100));

        BigDecimal percentage = BigDecimal.valueOf(1)
                .divide(BigDecimal.valueOf(8), 4, BigDecimal.ROUND_FLOOR)
                .multiply(BigDecimal.valueOf(100));
        System.out.println("percentage = " + percentage);


    }

    @Test
    void test3() {

        BigDecimal bigDecimal = BigDecimal.valueOf(2.1234);

        System.out.println("bigDecimal = " + bigDecimal);

        System.out.println(String.format("%.1f", bigDecimal));

    }

}
