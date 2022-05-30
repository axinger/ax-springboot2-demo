package com.ax.demo.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.ax.demo.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class JsonConfigTest {


    @Test
    public void testnull() {


        final Person person = Person.builder()
                .string2("jim")
                .list2(Arrays.asList("a", "b"))
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

        System.out.println("person = " + person);

        final List<Person> list = Arrays.asList(person);
        final String string = JSON.toJSONString(list);
        System.out.println("string = " + string);
        System.out.println("JSONObject.toJSONString(list) = " + JSONObject.toJSONString(list));
    }
}
