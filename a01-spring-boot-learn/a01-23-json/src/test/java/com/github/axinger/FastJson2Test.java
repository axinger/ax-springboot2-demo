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

//        Object eval = JSONPath.eval(person, "[age = 10]");
//        System.out.println("eval = " + eval);
        Object eval2 = JSONPath.eval(person, "@[?age=10]");
        System.out.println("eval2 = " + eval2);

    }

    @Test
    void test50() {
        /*
    @ 表示当前对象。
    [?age=10] 是一个过滤条件，表示选择 age 属性等于 10 的对象。
    因此，@[?age=10] 的含义是：在当前对象的集合中，选择所有 age 属性等于 10 的对象。


    $.age:
$ 表示根对象。
$.age 表示根对象中的 age 属性。
这种语法通常用于 JSON 对象或 JSON 数组的根级别。
@.age:
@ 表示当前对象。
@.age 表示当前对象中的 age 属性。
这种语法在某些情况下更明确地指定了当前对象。
         */
        Map<String, Object> map = new HashMap<>();
        map.put("age", 10);



        // 存在key
        JSONPath.set(map, "$.age", 11);
        System.out.println("map = " + map);

        Object eval = JSONPath.eval(map, "$.age");
        System.out.println("eval = " + eval);

        JSONPath.set(map, "@.age", 12);
        System.out.println("map = " + map);

        Object eval12 = JSONPath.eval(map, "@.age");
        System.out.println("eval12 = " + eval12);


        JSONPath.set(map, "$.name", "jim");
        System.out.println("map = " + map);

        JSONPath.set(map, "$.age", 10);
        System.out.println("map = " + map);

        // 修正 JSONPath 表达式
        Object eval2 = JSONPath.eval(map, "@[?age=10]");
        System.out.println("eval2 = " + eval2);

        Object eval3 = JSONPath.eval(map, "@[?(@.age=10)]");
        System.out.println("eval3 = " + eval3);
    }

    @Test
    void test501() {
        Map<String, Object> root = new HashMap<>();
        root.put("age", 10);

        Map<String, Object> nestedMap = new HashMap<>();
        nestedMap.put("age", 20);
        nestedMap.put("name", "nested");

        root.put("nested", nestedMap);

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> listMap1 = new HashMap<>();
        listMap1.put("age", 30);
        listMap1.put("name", "list1");
        list.add(listMap1);

        Map<String, Object> listMap2 = new HashMap<>();
        listMap2.put("age", 40);
        listMap2.put("name", "list2");
        list.add(listMap2);

        root.put("list", list);

        System.out.println("root = " + root);

        // 使用 $.age 和 @.age 进行设置和评估
        JSONPath.set(root, "$.age", 100);
        System.out.println("After setting $.age: " + root);

        JSONPath.set(root, "@.age", 200);
        System.out.println("After setting @.age: " + root);

        JSONPath.set(root, "$.nested.age", 200);
        System.out.println("After setting $.nested.age: " + root);

        JSONPath.set(root, "@.nested.age", 300);
        System.out.println("After setting @.nested.age: " + root);

        JSONPath.set(root, "$.list[0].age", 300);
        System.out.println("After setting $.list[0].age: " + root);

        JSONPath.set(root, "@.list[0].age", 400);
        System.out.println("After setting @.list[0].age: " + root);
        /*
        分析
        根对象设置:
        JSONPath.set(root, "$.age", 100);
        $.age 设置根对象的 age 属性为 100。
        JSONPath.set(root, "@.age", 200);
        @.age 也设置根对象的 age 属性为 200。
        效果相同：因为 root 是根对象，$ 和 @ 在这里指向同一个对象。
        嵌套对象设置:
        JSONPath.set(root, "$.nested.age", 200);
        $.nested.age 设置 nested 对象的 age 属性为 200。
        JSONPath.set(root, "@.nested.age", 300);
        @.nested.age 也设置 nested 对象的 age 属性为 300。
        效果相同：因为 nested 是 root 的一个属性，$ 和 @ 在这里指向同一个对象。
        数组元素设置:
        JSONPath.set(root, "$.list[0].age", 300);
        $.list[0].age 设置 list 数组中第一个元素的 age 属性为 300。
        JSONPath.set(root, "@.list[0].age", 400);
        @.list[0].age 也设置 list 数组中第一个元素的 age 属性为 400。
        效果相同：因为 list 是 root 的一个属性，$ 和 @ 在这里指向同一个对象。
         */
    }

    @Test
    void test502() {
        Map<String, Object> root = new HashMap<>();
        root.put("age", 10);

        Map<String, Object> nestedMap = new HashMap<>();
        nestedMap.put("age", 20);
        nestedMap.put("name", "nested");

        root.put("nested", nestedMap);
        System.out.println("root = " + root);


        JSONPath.set(root, "$.nested.@.age", 250);
        System.out.println("After setting $.nested.@.age: " + root);

        JSONPath.set(root, "@.nested.@.age", 350);
        System.out.println("After setting @.nested.@.age: ============" + root);

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
