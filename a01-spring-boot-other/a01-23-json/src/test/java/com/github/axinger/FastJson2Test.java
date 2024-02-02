package com.github.axinger;


import com.alibaba.fastjson2.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

class FastJson2Test {

    static Person person;

    @BeforeAll
    public static void before() {
        person = Person.builder()
                .id("1")
                .name("jin")
                .age(10)
                .books(List.of(

                        Book.builder()
                                .id("1")
                                .name("西游记")
                                .build(),

                        Book.builder()
                                .id("2")
                                .name("水浒传")
                                .build()
                ))
                .build();
    }

    @Test
    void test1() throws JsonProcessingException {

        Person person = Person.builder()
                .id("1")
                .name("jin")
                .books(List.of(

                        Book.builder()
                                .id("1")
                                .name("西游记")
                                .build(),

                        Book.builder()
                                .id("2")
                                .name("水浒传")
                                .build()
                ))
                .build();

        Map map = JSON.parseObject(JSON.toJSONString(person), Map.class);

        JSONObject jsonObject = JSONObject.from(map);

//        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(person), JSONObject.class);


        String book1Name = jsonObject.getJSONArray("books").getJSONObject(1).getString("name");
        System.out.println("book1Name = " + book1Name);
        jsonObject.getJSONArray("books").getJSONObject(1).put("name", "tom");
        System.out.println("jsonObject = " + jsonObject);
        System.out.println("map = " + map);
    }


    @Test
    void test_JSONObject() {
        Person person = Person.builder()
                .id("1")
                .name("jin")
                .books(List.of(
                        Book.builder()
                                .id("1")
                                .name("西游记")
                                .build(),
                        Book.builder()
                                .id("2")
                                .name("水浒传")
                                .build()
                ))
                .build();
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(person), new TypeReference<>() {
        });
        JSONObject jsonObject = new JSONObject(map);
        jsonObject.put("name", "修改用户名");
        jsonObject.getJSONArray("books").getJSONObject(1).put("name", "修改书名");
        System.out.println("jsonObject2 = " + jsonObject);
    }

    @Test
    void test_JSONObject2() {

        Person person = Person.builder()
                .id("1")
                .name("jin")
                .books(List.of(
                        Book.builder()
                                .id("1")
                                .name("西游记")
                                .build(),
                        Book.builder()
                                .id("2")
                                .name("水浒传")
                                .build()
                ))
                .build();

        JSONObject jsonObject = JSONObject.from(person);
        jsonObject.put("name", "修改用户名");
        jsonObject.getJSONArray("books").getJSONObject(1).put("name", "修改书名");
        System.out.println("JSONObject.from = " + jsonObject);

    }

    @Test
    void test3() throws JsonProcessingException {


        Person person = Person.builder()
                .id("1")
                .name("jin")
                .books(List.of(

                        Book.builder()
                                .id("1")
                                .name("西游记")
                                .build(),

                        Book.builder()
                                .id("2")
                                .name("水浒传")
                                .build()
                ))
                .build();


        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(person);

        JsonNode jsonNode = objectMapper.readTree(s);


        System.out.println("jsonNode.get(\"name\").asText() = " + jsonNode.get("name").asText());

        System.out.println("jsonNode.path(\"name\").asText() = " + jsonNode.path("name").asText());


    }


    @Test
    void test4() {

        System.out.println("person = " + person);

        String jsonString = JSONObject.toJSONString(person);
        System.out.println("jsonString = " + jsonString);


        Person person1 = JSONObject.parseObject(jsonString).to(Person.class);
        System.out.println("person1 = " + person1);

    }

    @Test
    void test5() {
        System.out.println("path修改值前person = " + person);

        JSONPath.set(person, "$.books[1].name", "红楼梦");

        System.out.println("path修改值后person = " + person);

        System.out.println("path修改值后books[1].name = " + JSONPath.of("$.books[1].name").eval(person));
        System.out.println("path修改值后books[3].name = " + JSONPath.of("$.books[3].name").eval(person));

        Object eval = JSONPath.eval(person, "[age = 10]");
        System.out.println("eval = " + eval);

    }

    @Test
    void test51() {

        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(person), new TypeReference<>() {
        });

        System.out.println("path修改值前map = " + map);

        JSONPath.set(map, "$.books[1].name", "红楼梦");

        System.out.println("path修改值后map = " + map);

        System.out.println("path修改值后books[1].name = " + JSONPath.of("$.books[1].name").eval(map));
        System.out.println("path修改值后books[1].name = " + JSONPath.eval(map, "$.books[1].name"));
        System.out.println("path修改值后books[3].name越界 = " + JSONPath.of("$.books[3].name").eval(map));
        System.out.println("path修改值后books[3].name越界 = " + JSONPath.eval(map, "$.books[3].name"));


    }

    @Test
    void test52() {

        Person person = new Person();
        person.setId("1");
        person.setAge(10);

//        Object eval = JSONPath.eval(person, "[age=10]");
        //fastjson2需要使用标准语法，如下：
//        Object eval = JSONPath.eval(person, "@[?age=10]");
        Object eval = JSONPath.eval(person, "[?(@.age=10)]");
        System.out.println("eval = " + eval);

    }

    //https://alibaba.github.io/fastjson2/jsonpath_cn.html
    @Test
    void test53() {
        List<Person> list = new ArrayList<>();


        {
            Person person = new Person();
            person.setId("1");
            person.setAge(10);
            list.add(person);
        }
        {
            Person person = new Person();
            person.setId("2");
            person.setAge(11);
            list.add(person);
        }

        @SuppressWarnings("unchecked")
        List<Person> list2 = (List<Person>) JSONPath.eval(list, "[?(@.age in (10))]");
        System.out.println("list2 = " + list2);

    }

    @Test
    void test55() {
//        String text = "{code:1,msg:'Hello world',data:{list:[1,2,3,4,5], ary2:[{a:2},{a:3,b:{c:'ddd'}}]}}";
//        JSONObject obj = JSONObject.parseObject(text);
//
//
//        System.out.println("obj = " + obj);
//
////        Object eval = JSONPath.eval(obj, "$..ary2[0].a");
//////        Object eval = JSONPath.eval(obj, "$..ary2");
////        System.out.println("eval = " + eval);


        String text = """
                {
                    "code": 1,
                    "data": {
                        "list": [
                            1,
                            2,
                            3,
                            4,
                            5
                        ],
                        "list2":{
                            "ary2": [
                                {
                                    "a": 2
                                },
                                {
                                    "a": 3,
                                    "b": {
                                        "c": 4
                                    }
                                }
                            ]
                        }
                    }
                }
                """;
        JSONObject obj = JSONObject.parseObject(text);

        Object eval = JSONPath.eval(obj, "$..ary2[0].a");
        System.out.println("eval = " + eval);
    }


    @Test
    void test8() {

        // 不可变集合,不能被修改
//        Map<String, Object> map = Map.of("name","jim");

        Map<String, Object> map = new HashMap<>();
        map.put("name", "jim");
        map.put("books", Arrays.asList("1", "2"));
//
//        JSONPath 	语义
//        $ 	根对象
//        $[-1] 	最后元素
//        $[:-2] 	第1个至倒数第2个
//        $[1:] 	第2个之后所有元素
//        $[1,2,3] 	集合中1,2,3个元素

        System.out.println("path修改值前map = " + map);
        JSONPath.of("$.name").set(map, "tom");
        JSONPath.set(map, "$.name", "红楼梦");
        JSONPath.set(map, "$.age", "红楼梦");
        JSONPath.set(map, "$.books[0]", "2");
        JSONPath.set(map, "$.books[-1]", "2");
        System.out.println("path修改值后map = " + map);

        JSONPath.of("$.books").arrayAdd(map, "4"); //将value字段的数组添加元素1,2,3

        System.out.println("path修改值后map = " + map);
    }

    @Test
    void test9() {
        JSONObject object = JSONObject.of("item", 1);
        JSONPath path = JSONPath.of("$.item");
        path.set(object, 2, JSONReader.Feature.DuplicateKeyValueAsArray);
        System.out.println("object = " + object);

        path.set(object, 3);
        System.out.println("object = " + object);

//        JSONPath.set(object,4,JSONReader.Feature.DuplicateKeyValueAsArray);
//        System.out.println("object = " + object);
    }

    @Test
    void test10() {

        // 不可变集合,不能被修改
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jim");

//        JSONObject from = JSONObject.from(map);
        JSONObject from = new JSONObject(map);

        map.put("name", "tom");
        System.out.println("from = " + from);
        System.out.println("map = " + map);


    }
}
