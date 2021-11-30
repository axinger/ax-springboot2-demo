package com.ax.jdk8;

import com.ax.demo_java.Person;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTest {

    @Test
    public void tes1() {

        Person person = null;

        Optional<Person> person1 = Optional.ofNullable(person);


        System.out.println("person1.orElse = " + person1.orElse(new Person()).getName());

        boolean present = person1.isPresent();
        if (present) {
            System.out.println("get = " + person1.get());
        }


    }
}
