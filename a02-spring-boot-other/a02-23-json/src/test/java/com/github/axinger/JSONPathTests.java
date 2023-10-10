package com.github.axinger;

import com.alibaba.fastjson.JSONPath;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class JSONPathTests {

    @Data
    public static class Entity {
        private Integer id;
        private String name;
        private Object value;

        public Entity() {
        }

        public Entity(Integer id, Object value) {
            this.id = id;
            this.value = value;
        }

        public Entity(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Entity(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public Object getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }


    @Test
    public void test_entity() throws Exception {
        Entity entity = new Entity(123, new Object());

        assertSame(entity.getValue(), JSONPath.eval(entity, "$.value"));
        assertTrue(JSONPath.contains(entity, "$.value"));
        assertEquals(2, JSONPath.size(entity, "$"));
        assertEquals(0, JSONPath.size(new Object[0], "$"));
    }


    @Test
    public void test2() {
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity("wenshao"));
        entities.add(new Entity("ljw2083"));

        List<String> names = (List<String>) JSONPath.eval(entities, "$.name"); // 返回enties的所有名称
        System.out.println("names = " + names);
        assertSame(entities.get(0).getName(), names.get(0));
        assertSame(entities.get(1).getName(), names.get(1));
    }

    @Test
    public void test3() {
        //返回集合中多个元素
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity("wenshao"));
        entities.add(new Entity("ljw2083"));
        entities.add(new Entity("Yako"));

        List<Entity> result = (List<Entity>) JSONPath.eval(entities, "[1,2]"); // 返回list中下标为1和2的元素
        System.out.println("result = " + result);


        assertEquals(2, result.size());
        assertSame(entities.get(1), result.get(0));
        assertSame(entities.get(2), result.get(1));
    }

    @Test
    public void test4() {

        //3.4 例4
        //按范围返回集合的子集
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity("wenshao"));
        entities.add(new Entity("ljw2083"));
        entities.add(new Entity("Yako"));

        List<Entity> result = (List<Entity>) JSONPath.eval(entities, "[0:2]"); // 返回下标从0到2的元素
        System.out.println("result = " + result);
        assertEquals(3, result.size());
//        assertSame(entities.get(0), result.get(0));
//        assertSame(entities.get(1), result.get(1));
//        assertSame(entities.get(2), result.get(1));
    }

    @Test
    public void test5() {
//        3.5 例5
//        通过条件过滤，返回集合的子集
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity(1001, "ljw2083"));
        entities.add(new Entity(1002, "wenshao"));
        entities.add(new Entity(1003, "yakolee"));
        entities.add(new Entity(1004, null));

        List<Object> result = (List<Object>) JSONPath.eval(entities, "[?(@.id in (1001,1002))]");
        System.out.println("result = " + result);
        assertEquals(2, result.size());
        assertSame(entities.get(0), result.get(0));
    }

    @Test
    public void test6() {
//        3.6 例6
//        根据属性值过滤条件判断是否返回对象，修改对象，数组属性添加元素
        Entity entity = new Entity(1001, "ljw2083");
        assertSame(entity , JSONPath.eval(entity, "[?(@.id = 1001)]"));

        JSONPath.set(entity, "id", 123456); //将id字段修改为123456
        System.out.println("entity = " + entity);
        assertEquals(123456, entity.getId().intValue());

        JSONPath.set(entity, "value", new int[0]); //将value字段赋值为长度为0的数组
    }


    @Test
    public void test7() {

        Map root = Collections.singletonMap("company",
                Collections.singletonMap("departs",
                        Arrays.asList(
                                Collections.singletonMap("id", 1001),
                                Collections.singletonMap("id", 1002),
                                Collections.singletonMap("id", 1003)
                        )
                ));

        //deepScan属性访问，例如$..name
        List<Object> ids = (List<Object>) JSONPath.eval(root, "$..id");

        System.out.println("ids = " + ids);


        Object eval = JSONPath.eval(root, "$..departs");
        System.out.println("eval = " + eval);

//        assertEquals(3, ids.size());
//        assertEquals(1001, ids.get(0));
//        assertEquals(1002, ids.get(1));
//        assertEquals(1003, ids.get(2));




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

        System.out.println(JSONPath.eval(person, "$..name"));

    }
}

