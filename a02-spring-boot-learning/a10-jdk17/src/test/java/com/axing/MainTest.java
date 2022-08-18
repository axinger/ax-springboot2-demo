package com.axing;

import com.axing.model.Person;
import org.junit.jupiter.api.Test;


class MainTest {

    @Test
    void test() {
        Person person = new Person();
        person.setName("jim");
        System.out.println("person = " + person);
    }
}