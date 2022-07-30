package com.xing;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.alibaba.fastjson2.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName MongoTemplateSQLTests.java
 * @description TODO
 * @createTime 2022年07月12日 16:32:00
 */

@SpringBootTest
@Slf4j
public class MongoTemplateSQLTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void test_sql_save() {

        for (int i = 1; i < 4; i++) {

            Map map = new HashMap();
            map.put("age", 10 + i);
            map.put("name", "jim" + i);
            map.put("birthday", LocalDateTimeUtil.parse(String.format("2020-01-%02d 12:00:00", i), "yyyy-MM-dd HH:mm:ss"));
            final Map student = mongoTemplate.save(map, "student");
            System.out.println("student = " + student);
        }

    }


    // SQL语句
    @Test
    void test_sql_find() {

        final FindIterable<Document> documents = mongoTemplate.getCollection("student")
                .find();
        List<Map> list = new ArrayList<>();
        documents.forEach(val -> {
            final Map<String, Object> map = val.entrySet().parallelStream()
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            map.put("id", String.valueOf(map.get("_id")));
            map.remove("_id");
            list.add(map);
        });

        System.out.println("list = " + list);
        System.out.println("JSON.toJSONString(list) = " + JSON.toJSONString(list));
    }

    @Test
    void test_sql_find_2() {

        Map sqlMap = new HashMap();
        sqlMap.put("age", 11);
        BasicDBObject basicDBObject = new BasicDBObject(sqlMap);
        final FindIterable<Map> findIterable = mongoTemplate.getCollection("student")
                .find(basicDBObject, Map.class);

        List<Map> list = new ArrayList<>();
        findIterable.forEach(val -> {
            val.put("id", String.valueOf(val.get("_id")));
            val.remove("_id");
            list.add(val);
        });

        System.out.println("list = " + list);
        System.out.println("JSON.toJSONString(list) = " + JSON.toJSONString(list));

    }

    @Test
    void test_sql_find_id() {


        Map sqlMap = new HashMap();
        sqlMap.put("_id", new ObjectId("62cd541108069b3d1bd92458"));
        sqlMap.put("age", 10);

        BasicDBObject basicDBObject = new BasicDBObject(sqlMap);
        final FindIterable<Document> documents = mongoTemplate.getCollection("student")
                .find(basicDBObject);
        List<Map> list = new ArrayList<>();
        documents.forEach(val -> {
            final Map<String, Object> map = val.entrySet().parallelStream()
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            map.put("id", String.valueOf(map.get("_id")));
            map.remove("_id");
            list.add(map);
        });

        System.out.println("list = " + list);
        System.out.println("JSON.toJSONString(list) = " + JSON.toJSONString(list));
    }

    @Test
    void test_sql_find_id_limit() {

        Map sqlMap = new HashMap();
        sqlMap.put("age", 10);

        BasicDBObject basicDBObject = new BasicDBObject(sqlMap);
        final FindIterable<Document> documents = mongoTemplate.getCollection("student")
                .find(basicDBObject)
                .limit(2);
        List<Map> list = new ArrayList<>();
        documents.forEach(val -> {
            final Map<String, Object> map = val.entrySet().parallelStream()
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            map.put("id", String.valueOf(map.get("_id")));
            map.remove("_id");
            list.add(map);
        });

        System.out.println("list = " + list);
        System.out.println("JSON.toJSONString(list) = " + JSON.toJSONString(list));
    }


}
