package com.xing.entity;

import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
@Slf4j
class UserTest {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void test_id() {
        ObjectId objectId = new ObjectId("A1234");
        System.out.println("objectId = " + objectId);

        System.out.println("ObjectId.get() = " + ObjectId.get());
    }

    @Test
    void test() {


        User user = new User();
        user.setName("jim");
        User save = mongoTemplate.save(user);


        System.out.println("user = " + user);
        System.out.println("save = " + save.getId());

        System.out.println(LocalDateTimeUtil.of(new ObjectId(save.getId()).getDate()));
    }
}