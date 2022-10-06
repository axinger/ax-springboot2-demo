package com.axing.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class JdbcTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void queryAll() {
//       执行sql语句
        String sql = "select * from t_person";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        System.out.println("list = " + list);
    }

    @Test
    public void timestamp() {
        final long time = new Date().getTime();
        final Timestamp timestamp = new Timestamp(time);
        System.out.println("time = " + time);
        System.out.println("timestamp = " + timestamp);

        final Timestamp timestamp1 = Timestamp.valueOf("3000-04-27 11:13:15");

        System.out.println("Timestamp.valueOf = " + timestamp1);
        System.out.println("timestamp1.getTime() = " + timestamp1.getTime());
        System.out.println("new Timestamp(32513713995L) = " + new Timestamp(32513713995000L));

//        final LocalDateTime now = LocalDateTime.now();
        LocalDate date = LocalDate.of(3000, 1, 1);
        LocalTime time2 = LocalTime.of(0, 0, 0);
        final LocalDateTime localDateTime = LocalDateTime.of(date, time2);
        System.out.println("Timestamp.valueOf(localDateTime) = " + Timestamp.valueOf(localDateTime));
    }

    //    增加用户
    @Test
    public void addUser() {
//        String sql = "insert into t_person(name,age,data) values ('小明','12')";
        final long time = new Date().getTime();
        final Timestamp timestamp = new Timestamp(time);
        System.out.println("time = " + time);
        System.out.println("timestamp = " + timestamp);
        final String sql = String.format("insert into t_person(name,age,data) values ('小明','12','%s')", timestamp);
        System.out.println("jdbcTemplate.update(sql) = " + jdbcTemplate.update(sql));

    }

    //更新用户
    @Test
    public void updateUser(@PathVariable("id") int id) {
        String sql = "update mybatis.user set name=?,pwd=? where id=" + id;
        Object[] objects = new Object[2];
        objects[0] = "小明2";
        objects[1] = "zxcv";
        jdbcTemplate.update(sql, objects);
    }

    //删除用户
    @Test
    public void delUser(@PathVariable("id") int id) {
        String sql = "delete from user where id=?";
        jdbcTemplate.update(sql, id);
    }
}
