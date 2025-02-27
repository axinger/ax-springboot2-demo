package com.github.axinger;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.github.axinger.model.Order;
import com.github.axinger.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Slf4j
@SpringBootTest
public class A04RedisApplicationTest {

    private static final String SERIAL_NUM = "order:serialNo:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisTemplate<String, User> redisTemplateUser;

    @Autowired
    private RedisTemplate<String, Order> redisTemplateOrder;

    @Test
    void test() {

//        Object peron = redisService.getCacheObject("peron");
//        System.out.println("peron = " + peron);

        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "jim");

        this.redisTemplate.opsForValue().set("demo:dog1", jsonObject);
        this.redisTemplate.opsForValue().set("demo:dog2", jsonObject);
    }

    @Test
    void test1_redisTemplateUser() {

        final User.Book book = User.Book.builder()
                .id(1)
                .name("海底两万里")
                .build();

        final User user = User.builder()
                .id(1)
                .name("jim")
                .age(21)
                .date(new Date())
                .books(List.of(book))
                .build();

        final String key = "test:user:1:User";
        this.redisTemplateUser.opsForValue().set(key, user);

        // 设置键的字符串值并返回其旧值
//        User andSet = this.redisTemplateUser.opsForValue().getAndSet(key, user);
//
//        System.out.println("andSet = " + andSet);

        // 可以直接存, 不能直接取
        // final Map user1 = (Map) this.redisTemplateUser.opsForValue().get(key);
        // System.out.println("user1 = " + user1);


        final User user2 = this.redisTemplateUser.opsForValue().get(key);

        System.out.println("user2 = " + user2);
    }


    @Test
    void test_map_to_user() {

        final String key = "test:user:1:Map";
        User user = redisTemplateUser.opsForValue().get(key);
        System.out.println("user = " + user);
    }

    @Test
    void test_json() {
        final Object o = this.redisTemplate.opsForValue().get("test:json:user.id");
        System.out.println("o = " + o);
    }

    @Test
    void test_get() {
        final Object o = this.redisTemplate.opsForValue().get("user:name");
        System.out.println("o = " + o);
    }

    @Test
    void test_Cacheable() {
        System.out.println("getByKey(1L) = " + getByKey(1L));
    }

    @Cacheable(key = "#id")
    public String getByKey(final Long id) {
        return "我的jim" + id;
    }

    @Test
    void test_expire() {

        final String key = "test:expire:1:Map";
        this.redisTemplate.opsForValue().set(key, "1");
        this.redisTemplate.expire(key, 5, TimeUnit.SECONDS);
    }

    /**
     * 自增流水号
     */
    @Test
    void orderSerialNo() {
        for (int i = 0; i < 100; i++) {
            testNum();
        }
    }

    void testNum() {
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime = dateTime.plusDays(1);
        final String currentDate = LocalDateTimeUtil.format(dateTime, "yyyy-MM-dd");
        String key = SERIAL_NUM + currentDate;
        // 过期时间 60*60*24
        long incr = redisTemplate.opsForValue().increment(key, 1);
        // 左对齐
        String value = StrUtil.padPre(String.valueOf(incr), 6, "0");
        // 然后把 时间戳和优化后的 ID 拼接
        String code = StrUtil.format("{}-{}", currentDate, value);
        System.out.println("code = " + code);
    }

    @Test
    public void testOrder() {
        final String key = "test-order:1";
        Order order = Order.builder()
                .id("1")
                .name("jim")
                .dateTime(LocalDateTime.now())
                .build();
        redisTemplate.opsForValue().set(key, order);
    }

    @Test
    public void testOrder2() {
        final String key = "test-order:1";

        Object o = redisTemplate.opsForValue().get(key);
        System.out.println("o = " + o);
        Class<?> aClass = o.getClass();
        System.out.println("aClass = " + aClass);

        if (o instanceof Order order) {
            System.out.println("order = " + order);
        }
    }

    @Test
    public void testOrder21() {
        final String key = "test-order:2";
        Order order = Order.builder()
                .id("1")
                .name("jim")
                .dateTime(LocalDateTime.now())
                .build();
        redisTemplateOrder.opsForValue().set(key, order);

        Object order1 = redisTemplateOrder.opsForValue().get(key);
        System.out.println("order1 = " + order1);
        if (order1 instanceof Order order2) {
            System.out.println("order2 = " + order2);
        }
    }


}
