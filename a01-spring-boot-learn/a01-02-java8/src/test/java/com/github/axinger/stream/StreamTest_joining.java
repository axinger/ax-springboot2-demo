package com.github.axinger.stream;

import com.github.axinger.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest_joining {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());

        String names = personList.stream().map(Person::getName).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名：" + names);


        List<String> list = Arrays.asList("A", "B", "C");
        String string = list.stream().collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串：" + string);

        String string2 = list.stream().collect(Collectors.joining("-", "头", "尾"));
        System.out.println("拼接后的字符串：" + string2);
    }
}
