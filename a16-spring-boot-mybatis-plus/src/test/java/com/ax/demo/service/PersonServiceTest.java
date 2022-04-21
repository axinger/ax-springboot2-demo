package com.ax.demo.service;

import com.ax.demo.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootTest
@Slf4j
class PersonServiceTest {

    @Autowired
    DataSource master;

    @Autowired
    DataSource slave_1;

    @Autowired
    private PersonService personService;

    @Test
    void add() {
        Person person = new Person();
        person.setName("jim");
        person.setAge(20);
        person.setData(new Date());
//        person.setTime();

        person.setTime(Timestamp.valueOf(LocalDateTime.now()));
        personService.save(person);
    }

    @Test
    void master() {
        System.out.println("master = " + master);
        System.out.println("slave_1 = " + slave_1);
    }

}
