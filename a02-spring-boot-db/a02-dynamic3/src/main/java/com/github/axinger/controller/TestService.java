package com.github.axinger.controller;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
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


    ///  这里自定义配置, 破坏了事务
    @DSTransactional
    public void test31() {
        test13();
        test14();
    }


    public void test13() {
        System.out.println("Person==========");
        Person dog = new Person();
        dog.setName("小明");
        personService.save(dog);
    }


    public void test14() {
        System.out.println("SysAnimalEntity==========");
        SysAnimalEntity entity = new SysAnimalEntity();
        entity.setName("狗");
        int i = 1 / 0;
        animalService.save(entity);
    }

    public void test12() {
        Dog dog = new Dog();
        dog.setName("加菲狗");
        dogService.save(dog);
    }


}
