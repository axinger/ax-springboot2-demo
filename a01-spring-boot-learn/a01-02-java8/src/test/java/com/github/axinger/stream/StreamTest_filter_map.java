package com.github.axinger.stream;

import com.github.axinger.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest_filter_map {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "tom", "man", 20, "安徽"));
        personList.add(new Person(2, "jack", "man", 22, "安徽"));
        personList.add(new Person(3, "jim", "man", 20, "江苏"));
        personList.add(new Person(4, "lili", "man", 24, "上海"));

        List<String> list = personList.stream().filter(x -> x.getAge() > 20)
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("age>20的 姓名：" + list);
    }
}
