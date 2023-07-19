package com.axing.demo.stream;

import com.axing.demo.Person;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//去重
public class StreamDistinctTests {

    @Test
    void test1() {
        String[] arr1 = {"a", "b", "c", "d"};
        String[] arr2 = {"d", "e", "f", "g"};
        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        // concat:合并两个流 distinct 清楚的：去重
        List<String> newList = Stream.concat(stream1, stream2).distinct().toList();
        System.out.println("流合并去重 distinct：" + newList);
    }


    @Test
    void test2() {

        List<Person> list2 = List.of(

                Person.builder()
                        .id(1)
                        .name("jim")
                        .age(10)
                        .address("安徽")
                        .build(),

                Person.builder()
                        .id(2)
                        .name("tom")
                        .age(10)
                        .address("安徽")
                        .build(),

                Person.builder()
                        .id(3)
                        .name("lili")
                        .age(11)
                        .address("江苏")
                        .build(),

                Person.builder()
                        .id(4)
                        .name("luck")
                        .age(11)
                        .address(null)
                        .build()
        );

        System.out.println("list = " + list2);
        // 单个属性去重
        ArrayList<Person> collect = list2.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(
                                Comparator.comparing(Person::getAge))), ArrayList::new));
        System.out.println("去重年龄重复的 = " + collect);

        // 多个属性去重
        List<Person> collect2 = list2.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(
                                Comparator.comparing(Person::getAge)
                                        .thenComparing(Person::getAddress, Comparator.nullsLast(Comparator.naturalOrder()))
                                //保留最后一个
                        )), ArrayList::new));
        System.out.println("多个属性去重collect2 = " + collect2);


        List<Person> collect3 = list2.stream()
                .filter(Objects::nonNull)  // 过滤掉 null 的 Person 对象
                .collect(Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(Person::getAge)
                                .thenComparing(Person::getAddress, Comparator.nullsLast(Comparator.naturalOrder()))
                )))
                .stream()
                .toList();

        System.out.println("多个属性去重collect3 = " + collect3);
    }


    //去重,保留想要的属性
    @Test
    void test3() {

        List<Person> list2 = List.of(

                Person.builder()
                        .id(1)
                        .name("jim")
                        .age(10)
                        .address("安徽")
                        .build(),

                Person.builder()
                        .id(2)
                        .name("tom")
                        .age(10)
                        .address("安徽")
                        .build(),

                Person.builder()
                        .id(3)
                        .name("lili")
                        .age(11)
                        .address("江苏")
                        .build(),

                Person.builder()
                        .id(4)
                        .name("moni")
                        .age(11)
                        .address("安徽")
                        .build(),

                Person.builder()
                        .id(5)
                        .name("luck")
                        .age(11)
                        .address(null)
                        .build()
        );


        List<Person> collect3 = list2.stream()
                .filter(Objects::nonNull)  // 过滤掉 null 的 Person 对象
                .collect(Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(Person::getAge)

                )))
                .stream()
                .toList();

        System.out.println("多个属性去重 collect3 = " + collect3);


        List<Person> collect4 = list2.stream()
                // 在 Stream 上进行过滤操作
                .sorted(Comparator.comparing((Person person) -> {
//                    if ("安徽".equals(person.getAddress())) {
//                        return -1;
//                    } else if ("江苏".equals(person.getAddress())) {
//                        return 0;
//                    } else {
//                        return 1;
//                    }

                    // 把想要的,排序最前面, TreeSet 会先插入
                    if ("安徽".equals(person.getAddress())) {
                        return -1;
                    } else {
                        return 1;
                    }
                }).thenComparing(Comparator.nullsLast(Comparator.comparing(Person::getName))))
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getAge))))
                .stream()
                .toList();
        System.out.println("多个属性去重 collect4 = " + collect4);
    }
}
