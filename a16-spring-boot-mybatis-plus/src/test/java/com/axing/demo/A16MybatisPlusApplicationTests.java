package com.axing.demo;

import com.axing.demo.domain.Person;
import com.axing.demo.service.PersonService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class A16MybatisPlusApplicationTests {


    @Autowired
    private PersonService personService;


    @Test
    void contextLoads() {

        final LambdaQueryWrapper<Person> queryWrapper = Wrappers.<Person>lambdaQuery()
                .eq(Person::getName, "jim")
                .last("limit 1");


//        final Person one = personService.getOne(queryWrapper, false);
        final Person one = personService.getOne(queryWrapper, false);
        System.out.println("one = " + one);


        final Person jim = personService.lambdaQuery()
                .eq(Person::getName, "jim")
                .last("limit 1")
                .one();
        System.out.println("jim = " + jim);
    }


    @Test
    void one() {


    }


}
