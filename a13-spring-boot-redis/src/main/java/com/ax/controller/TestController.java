package com.ax.controller;


import com.ax.model.Person;
import com.ax.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class TestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;


    @GetMapping("/addVal")
    public void addValue() {
        stringRedisTemplate.<String, String>opsForValue().set("myName", "jim");
    }


    @GetMapping("/getVal")
    public Object getValue() {
        Map map = new HashMap();
        String myName = stringRedisTemplate.opsForValue().get("myName");
        System.out.println("myName = " + myName);
        map.put("name", myName);
        return map;
    }

    @RequestMapping("/get")
    public Object getUser(Integer id) {
        System.out.println("getUser...........");
        return userService.findUser(id);
    }

    @RequestMapping("/update")
    public Object update(Integer id) {
        Person person = Person.builder().id(id).name("jim" + new Random().nextInt(100)).age(10).build();


        return userService.updateUser(person);
    }

    @RequestMapping("/delete")
    public boolean deleteUser(Integer id) {
        System.out.println("deleteUser...........");
        userService.deleteUser(id);
        return true;
    }


    /**
     * @Description: @Caching是缓存的结合体，可以同时设置多了缓存的信息设置。
     */
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp", key = "#name")
            },
            put = {                  //更新缓存可以通过id，email或者lastName进行key值查找。
                    @CachePut(value = "emp", key = "#result.id"),
                    @CachePut(value = "emp", key = "#result.email"),
                    @CachePut(value = "emp", key = "#result.name"),
            }
    )
    public Person getEmpByLastName(String name) {
        System.out.println("要查询的用户名为：" + name);
        return new Person(1, "jim", 10);
    }


}
