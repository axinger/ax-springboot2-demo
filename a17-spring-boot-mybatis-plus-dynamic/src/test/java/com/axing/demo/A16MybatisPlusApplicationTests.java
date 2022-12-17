package com.axing.demo;

import com.axing.demo.domain.Person;
import com.axing.demo.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class A16MybatisPlusApplicationTests {


    @Autowired
    private PersonService personService;


    @Test
    void test1() {

        Integer age = 10;

        final Person jim = personService.lambdaQuery()
                .eq(Person::getName, "jim")
                // .eq(age > 10, Person::getAge, age)
                .last("limit 1")
                .one();
        System.out.println("jim = " + jim);
    }


}
