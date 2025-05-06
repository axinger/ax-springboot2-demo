package com.github.axinger;


import com.github.axinger.model.Person;
import org.junit.Test;

public class NonNullTests {

    public void getName(@lombok.NonNull String name) {

        System.out.println("name = " + name);
    }

    @Test
    public void test() {


        getName(null);
    }

    @Test
    public void test1() {
        Person person = new Person();
        person.setName(null);
        person.setAddress(null);
        System.out.println("person = " + person);
    }
}
