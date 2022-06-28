package com.xing;

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

import java.time.LocalDateTime;
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

        Map map = new HashMap();
        map.put("age", 10);
        map.put("name", "jim");
        map.put("birthday", LocalDateTime.now());
        final Map student = mongoTemplate.save(map, "student");

        System.out.println("student = " + student);
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
