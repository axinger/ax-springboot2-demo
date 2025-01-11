//package com.github.axinger.controller;
//
//import com.github.axinger.service.DogService;
//import com.github.axinger.service.PersonService;
//import com.github.axinger.service.SysAnimalService;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
/// **
// * @author xing
// * @version 1.0.0
// * @ClassName TestController.java
// * @description TODO
// * @createTime 2022年07月27日 10:56:00
// */
//@RestController
//@Tag(name = "TestController", description = "测试控制器")
//public class TestController {
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
//    @Autowired
//    private TestService testService;
//
//    @GetMapping("/1")
//    public Object test1() {
//        return personService.list();
//    }
//
//    @GetMapping("/2")
//    public Object test2() {
//        return dogService.list();
//    }
//
//    @GetMapping("/3")
//    public void test3(@RequestParam(required = true) Boolean error) {
//        testService.testAB(error);
//    }
//
//
//    @GetMapping("/4")
//    public void testAC(@RequestParam(required = true) Boolean error) {
//        testService.testAC(error);
//    }
//
//
//}
