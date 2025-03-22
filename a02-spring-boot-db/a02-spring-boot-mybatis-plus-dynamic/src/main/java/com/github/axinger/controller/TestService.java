//package com.github.axinger.controller;
//
//import com.baomidou.dynamic.datasource.annotation.DSTransactional;
//import com.github.axinger.annotation.DB1;
//import com.github.axinger.db2.domain.SysAnimalEntity;
//import com.github.axinger.db2.service.SysAnimalService;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//@AllArgsConstructor
//public class TestService {
//
//
//    @Autowired
//    private PersonService personService;
//
//    @Autowired
//    private DogService dogService;
//
//    @Autowired
//    private SysAnimalService animalService;
//
//
//    @DB1
//    @DSTransactional
//    public void testAB(boolean error) {
//        testPerson();
//        testSysAnimalEntity(error);
//    }
//
//    @DB1
//    @DSTransactional
//    public void testAC(boolean error) {
//        testPerson();
//        testDog(error);
//    }
//
//
//    public void testPerson() {
//        System.out.println("Person==========");
//        Person dog = new Person();
//        dog.setName("小明");
//        personService.save(dog);
//    }
//
//
//    public void testSysAnimalEntity(boolean error) {
//        System.out.println("SysAnimalEntity==========");
//        SysAnimalEntity entity = new SysAnimalEntity();
//        entity.setName("狗");
//        if (error) {
//            int i = 1 / 0;
//        }
//        animalService.save(entity);
//    }
//
//    public void testDog(boolean error) {
//        Dog dog = new Dog();
//        dog.setName("加菲狗");
//        if (error) {
//            int i = 1 / 0;
//        }
//        dogService.save(dog);
//    }
//
//
//}
