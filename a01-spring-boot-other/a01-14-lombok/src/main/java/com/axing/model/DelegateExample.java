package com.axing.model;


import lombok.experimental.Delegate;

public class DelegateExample {

    @Delegate
    private final AClass aClass = new AClass();


    public static void main(String[] args) {
        DelegateExample example = new DelegateExample();
        // 调用其他内方法,不用继承
        example.test();
    }
}
