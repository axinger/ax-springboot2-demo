package com.github.axinger;

import com.github.axinger.domain.Dog;
import com.github.axinger.domain.Person;
import com.github.axinger.service.DogService;
import com.github.axinger.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Demo02DynamicDatasourceApplicationTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private DogService dogService;

    @Test
    void test1() {
        Person person = new Person();
        person.setName("tom");
        personService.save(person);
    }

    @Test
    void test12() {
        List<Person> list = personService.list();
        System.out.println("list = " + list);
    }

    @Test
    void test2() {
        Dog dog = new Dog();
        dog.setName("高飞狗");
        dogService.save(dog);
    }


}
