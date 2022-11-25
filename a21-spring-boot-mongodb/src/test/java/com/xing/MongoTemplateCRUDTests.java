package com.xing;

import cn.hutool.core.lang.func.LambdaUtil;
import com.mongodb.client.result.UpdateResult;
import com.xing.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j
public class MongoTemplateCRUDTests {


    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 插入多个,只有insertAll
     */
    @Test
    void save_user() {

        List list = new ArrayList<>(){{
            {
                User user = new User();
                user.setId("1");
                user.setName("jim");
                user.setAge(10);
                add(user);
            }
            {
                User user = new User();
                user.setId("2");
                user.setName("tom");
                user.setAge(10);
                add(user);
            }
            {
                User user = new User();
                user.setId("3");
                user.setName("jack");
                user.setAge(10);
                add(user);
            }
            {
                User user = new User();
                user.setId("4");
                user.setName("lili");
                user.setAge(10);
                add(user);
            }
        }};
        mongoTemplate.<User>insertAll(list);
        //mongoTemplate.save(list,"user"); //失败
    }


    /**
     * 只更新查询到的第一个,一般用在模糊搜索中
     */
    @Test
    void updateFirst() {

        // 更新审核实例
        Query query = new Query(Criteria
                .where(LambdaUtil.getFieldName(User::getAge)).is(10)
                // ne 不等于
                .and(LambdaUtil.getFieldName(User::getName)).ne("jim")
        );

        // 会更新第一个 age =10, 且age!=jim的
        Update update = new Update();
        update.set(LambdaUtil.getFieldName(User::getAge), 20);

        UpdateResult result = this.mongoTemplate.updateFirst(query, update, User.class);
        System.out.println("result = " + result);
    }

    /**
     * 迷糊查询
     */
    @Test
    void find() {

        Query query = new Query(Criteria
                .where(LambdaUtil.getFieldName(User::getAge)).is(10)
        );

        User one = this.mongoTemplate.findOne(query, User.class);
        System.out.println("one = " + one);


        List<User> allList = this.mongoTemplate.findAll(User.class);
        System.out.println("allList = " + allList);


        List<User> list = this.mongoTemplate.find(query, User.class);
        System.out.println("list = " + list);

        //  skip 跳过1个, limit 查询几个, 不要用这个做分页
        query.skip(1);
        query.limit(2);
        List<User> limitList = this.mongoTemplate.find(query, User.class);
        System.out.println("limitList = " + limitList);

    }

    @Test
    void find_page() {
        Query query = new Query(Criteria
                .where(LambdaUtil.getFieldName(User::getAge)).is(10)
        );

        long count = mongoTemplate.count(query, User.class);
        System.out.println("count = " + count);

        PageRequest request = PageRequest.of(0, 2);
        query.with(request);

        List<User> list = this.mongoTemplate.find(query, User.class);
        System.out.println("list = " + list);
        System.out.println("request = " + request);

        //System.out.println("request.isPaged() = " + request.isPaged());
        //System.out.println("request.isUnpaged() = " + request.isUnpaged());
        //System.out.println("request.getPageNumber() = " + request.getPageNumber());
        //System.out.println("request.getPageSize() = " + request.getPageSize());
        //System.out.println("request.getOffset() = " + request.getOffset());




        // 分页,需要总数量, 需要自定义查询总数量
        //PageableExecutionUtils.getPage()
        Page page = new PageImpl<>(list, request, count);
        System.out.println("page = " + page);

        //mongoTemplate.

        // 有很多判断
        Page<User> page1 = PageableExecutionUtils.getPage(list, request, () -> count);
        System.out.println("page1 = " + page1);

        System.out.println("page1.getTotalPages() = " + page1.getTotalPages());
        System.out.println("page1.isFirst() = " + page1.isFirst());
        System.out.println("page1.isLast() = " + page1.isLast());
        System.out.println("page1.hasNext() = " + page1.hasNext());
        System.out.println("page1.getNumber() = " + page1.getNumber());
        System.out.println("page1.getTotalElements() = " + page1.getTotalElements());
    }


    /**
     * 只更新查询到的第一个,用主键
     */
    @Test
    void updateFirst_for_id() {

        // 更新审核实例
        Query query = new Query(Criteria
                .where("_id").is(2)
        );

        // 会更新第一个 age =10, 且age!=jim的
        Update update = new Update();
        update.set(LambdaUtil.getFieldName(User::getAge), 20);

        UpdateResult result = this.mongoTemplate.updateFirst(query, update, User.class);
        System.out.println("result = " + result);
    }

    /**
     * 更新多个
     */
    @Test
    void updateMulti() {

        // 更新审核实例
        Query query = new Query(Criteria
                .where(LambdaUtil.getFieldName(User::getAge)).is(10)
                // ne 不等于
                .and(LambdaUtil.getFieldName(User::getName)).ne("jim")
        );

        Update update = new Update();
        update.set(LambdaUtil.getFieldName(User::getAge), 20);

        UpdateResult result = this.mongoTemplate.updateMulti(query, update, User.class);
        System.out.println("result = " + result);

    }




}
