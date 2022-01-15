package com.ax.demo_java.string;

public class StringTest {

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
