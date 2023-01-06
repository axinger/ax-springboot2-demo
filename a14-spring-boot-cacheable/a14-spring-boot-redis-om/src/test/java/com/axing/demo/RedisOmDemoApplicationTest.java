package com.axing.demo;

import com.axing.demo.dao.PeopleRepository;
import com.axing.demo.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RedisOmDemoApplicationTest {

    @Autowired
    PeopleRepository repo;

    @Test
    void tes1() {
        List<Person> list = repo.findAll();
        System.out.println("list = " + list);
    }


    @Test
    void tes2() {
        Person person = new Person();
        person.setFirstName("Apple iPhone 14");
        Person person1 = repo.save(person);
        System.out.println("person1 = " + person1);
    }
}
