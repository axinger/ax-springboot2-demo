package com.github.axinger.stream;

import com.github.axinger.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StreamTest_max_str {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(2)
                .build());
        personList.add(Person.builder()
                .id(3)
                .build());
        personList.add(Person.builder()
                .id(4)
                .build());
        personList.add(Person.builder()
                .id(5)
                .build());
        personList.add(Person.builder()
                .id(6)
                .build());

        final Optional<Person> optionalPerson = personList.stream().max(Comparator.comparingInt(Person::getAge));
        System.out.println("员工工资最大值：" + optionalPerson.get().getAge());

        final Optional<String> optional = personList.stream().max(Comparator.comparingInt(Person::getAge)).map(Person::getName);
        System.out.println("员工工资最大值的姓名 = " + optional.orElse(null));
    }
}
