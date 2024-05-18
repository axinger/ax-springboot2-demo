package com.github.axinger.model;

import org.junit.Test;

public class ImmutablePersonTest {

    @Test
    public void test1() {
        ImmutablePerson person = new ImmutablePerson("jim", 20);

        System.out.println("person = " + person);

        System.out.println("person.getName() = " + person.getName());


        // 新对象
        ImmutablePerson person1 = person.withAge(21).withName("tom");

        System.out.println("person1 = " + person1);


    }

}
