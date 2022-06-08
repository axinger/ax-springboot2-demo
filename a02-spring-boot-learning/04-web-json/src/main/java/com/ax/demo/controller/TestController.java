package com.ax.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.ax.demo.entity.Person;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * @author xing
 */


@RestController
public class TestController {

    @RequestMapping(value = "/json")
    public Object testnull() {

        final Person person = Person.builder()
                .string2("jim")
                .list2(Lists.newArrayList("a", "b"))
                .map2(new HashMap<String, Object>() {{
                    put("age", 10);
                }})
                .aBoolean2(true)
                .localDateTime(LocalDateTime.now())
                .date(new Date())
                .aDouble(2.12433)
                .aFloat(2.12533f)
                .aLong(2222L)
                .build();

        Person person1 = new Person();
        person1.setString2("tom");
        final List<Person> list = Lists.newArrayList(person, person1);


        final String string = JSON.toJSONString(list);
        System.out.println("string = " + string);
        System.out.println("JSONObject.toJSONString(list) = " + JSONObject.toJSONString(list));


        return list;
    }


}
