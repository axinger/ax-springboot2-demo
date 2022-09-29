package com.axing.demo.controller;


import com.axing.demo.entity.EmptyModel;
import com.axing.demo.entity.Person;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author xing
 */


@RestController
public class TestController {

    @RequestMapping(value = "/json")
    public Object json() {

        Map map = new HashMap();
        map.put("localDateTime", LocalDateTime.now());
        map.put("localDate", LocalDate.now());
        map.put("localTime", LocalTime.now());
        map.put("date", new Date());

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

        map.put("personList", list);

        EmptyModel emptyModel = new EmptyModel();
        map.put("emptyModel", emptyModel);


        return map;
    }


}
