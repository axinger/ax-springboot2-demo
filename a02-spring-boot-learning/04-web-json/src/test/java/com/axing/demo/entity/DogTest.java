package com.axing.demo.entity;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;

class DogTest {

    @Test
    void test1() throws Exception {
        final Dog dog = new Dog();
        dog.setString("不使用builder");
        dog.setString2("str2");
        dog.setATransient("首部1个小写字母,transient不被序列化,失效");
        dog.setAaTransient("首部2个以上小写字母,transient修改不被序列化,正常");
        System.out.println("JSON.toJSONString(dog) = " + JSON.toJSONString(dog));

        final Dog dog1 = Dog.builder()
                .string("使用builder")
                .aTransient("首部1个小写字母,transient不被序列化,失效")
                .aaTransient("首部2个以上小写字母,transient修改不被序列化,正常")
                .build();
        System.out.println("builder JSON.toJSONString(dog1) = " + JSON.toJSONString(dog1));


        Person person = new Person();
        person.setAaTransient("transient修改不被序列化");
        System.out.println("JSON.toJSONString(person) = " + JSON.toJSONString(person));


        final Person person2 = Person.builder()
                .aTransient("transient修改不被序列化")
                .aaTransient("transient修改不被序列化")
                .build();
        System.out.println("builder JSON.toJSONString(person2) = " + JSON.toJSONString(person2));

    }


}
