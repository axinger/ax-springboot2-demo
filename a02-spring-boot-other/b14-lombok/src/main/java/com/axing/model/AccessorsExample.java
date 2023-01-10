package com.axing.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(
        // getAge()-> age(), setAge(int age)-> age(int age)
        fluent = true,
        // void setAge(int age)   ->  AccessorsExample setAge(int age), 就可以连续调用了
        chain = true
)
public class AccessorsExample {
    @Getter
    @Setter
    private int age = 10;
}

@ToString
class PrefixExample {
    /**
     * 加前缀
     */
    @Accessors(prefix = "f")
    @Getter
    private String fName = "Hello, World!";


    public static void main(String[] args) {
        PrefixExample example = new PrefixExample();
        System.out.println("example = " + example);
    }
}


