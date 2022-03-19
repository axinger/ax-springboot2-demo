package com.ax.a16.service;

import com.ax.a16.domain.TPerson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
@Slf4j
class TPersonServiceTest {

    @Autowired
    TStudentService studentService;
    @Autowired
    DataSource master;
    @Autowired
    DataSource slave_1;
    @Autowired
    private TPersonService personService;

    @Test
    void add() {
        TPerson person = new TPerson();
        person.setName("jim");
        person.setAge(20);
        personService.save(person);
    }

    @Test
    void master() {
        System.out.println("master = " + master);
        System.out.println("slave_1 = " + slave_1);
    }

    @Test
    void getAll() {
        System.out.println("personService.getById(1L) = " + personService.getById(1L));
        System.out.println("studentService.getById(1L) = " + studentService.getById(1L));
    }
}
