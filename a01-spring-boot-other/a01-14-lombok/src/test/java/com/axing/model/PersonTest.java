package com.axing.model;


import org.junit.Test;

public class PersonTest {

    @Test
    public void test1() {

        Person person = new Person();
        person.setName(null);
//        person.setAddress(null);
        System.out.println("person = " + person);
    }

}
