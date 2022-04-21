package com.ax.test.java;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

public class StringTest {


    @Test
    void test_format(){

//       String url = String.format("%s/%s", "aaaa", "b");
//        System.out.println("url = " + url);
        String val = "1";
        System.out.println("Boolean.parseBoolean(val) = " + Boolean.parseBoolean(val));
//
        String val2 = "1";
        if (StrUtil.isNumeric(val2)) {
            System.out.println("Integer.parseInt(val) = " + Integer.parseInt(val2));
        }

    }

    @Test
    void test2(){
        String str ="http://localhost";


//        //判断最后一个字符是否为“段”
//        if (str.endsWith("/")) {
//            str = str.substring(0,str.length() - 1);
//        }


        System.out.println("str = " + str);
        final String s = StringUtils.trimTrailingCharacter(str, '/');
        System.out.println("s = " + s);


        final String th = StringUtils.replace(str, "https", "tcp");
        System.out.println("th = " + th);
        System.out.println("str = " + str);

    }

    String string = new String("good");
    char[] chars = {'t', 'e', 's', 't'};
    StringBuffer string2 = new StringBuffer("good");

    public static void main(String[] args) {
        StringTest test = new StringTest();
        test.change(test.string, test.chars);
        System.out.println("test.string = " + test.string);
        System.out.println(test.chars);


        test.change2(test.string2);
        System.out.println("test.string2 = " + test.string2);

        System.out.println(1 << 1);
        System.out.println(1 << 2);
        System.out.println(1 << 3);

        System.out.println(2 << 1);
        System.out.println(2 << 2);


        StringBuffer string2 = new StringBuffer("good");
        System.out.println("delete = " + string2.delete(2, 6));
    }

    public void change(String string, char[] ch) {
        string = "test ok";
        ch[0] = 'b';
    }

    public void change2(StringBuffer string2) {
        string2 = new StringBuffer("test ok");

    }




}
