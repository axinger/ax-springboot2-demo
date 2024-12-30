package com.github.axinger.controller;

import com.github.axinger.db.master.domain.Person;
import com.github.axinger.db.master.domain.SysAnimalEntity;
import com.github.axinger.db.master.service.PersonService;
import com.github.axinger.db.master.service.SysAnimalService;
import com.github.axinger.db.slave.domain.Dog;
import com.github.axinger.db.slave.service.DogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TestService {


    @Autowired
    private PersonService personService;

    @Autowired
    private DogService dogService;

    @Autowired
    private SysAnimalService animalService;

    public void testAB() {
        testA();
        testB();
    }


    public void testAC() {
        testA();
        testC();
    }


    public void testA() {
        System.out.println("Person==========");
        Person dog = new Person();
        dog.setName("小明");
        personService.save(dog);
    }


    public void testB() {
        System.out.println("SysAnimalEntity==========");
        SysAnimalEntity entity = new SysAnimalEntity();
        entity.setName("狗");
//        int i = 1 / 0;
        animalService.save(entity);
    }

    public void testC() {
        Dog dog = new Dog();
        dog.setName("加菲狗");
        dogService.save(dog);
    }


}
