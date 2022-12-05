package com.axing.demo;

// 泛型接口。
public interface AnimalAction<T> {
// public interface AnimalAction<T extends Animal> {


    T show(T t);

    T show1(Class<T> tClass);

    T show2();
}
