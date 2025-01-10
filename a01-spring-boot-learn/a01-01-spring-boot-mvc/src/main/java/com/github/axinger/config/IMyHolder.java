package com.github.axinger.config;

public interface IMyHolder {

    static <T> void setId(T context) {

    }

    static <T> T getId() {
        return null;
    }

    static void removeId() {

    }

//
//    // 抽象方法
//    void abstractMethod();
//
//    // 默认方法
//    default void defaultMethod() {
//        System.out.println("Default method implementation");
//    }
//
//    // 静态方法
//    static void staticMethod() {
//        System.out.println("Static method implementation");
//    }

}
