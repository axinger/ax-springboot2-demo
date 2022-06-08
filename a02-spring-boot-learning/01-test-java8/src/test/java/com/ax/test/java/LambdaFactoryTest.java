package com.ax.test.java;

import com.ax.demo.Person;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用构造器,类似方法,要有对应的构造函数
 */
public class LambdaFactoryTest {

    @Test
    void test1() {

        Supplier<Person> supplier = () -> new Person("jim", 12);

        System.out.println(supplier.get());

        Supplier<Person> supplier2 = Person::new;
        System.out.println("supplier2 = " + supplier2.get());
    }

    @Test
    void test2() {

        Function<String, Person> function1 = name -> new Person(name);
        Person person1 = function1.apply("jim");
        System.out.println("person1 = " + person1);

        Function<String, Person> function2 = Person::new;
        Person person = function1.apply("jim");
        System.out.println("person = " + person);
    }

}
