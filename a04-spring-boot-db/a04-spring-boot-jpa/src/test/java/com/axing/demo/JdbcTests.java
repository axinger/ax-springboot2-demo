package com.axing.demo;

import com.axing.demo.dao.UsersJpaRepository;
import com.axing.demo.model.Book;
import com.axing.demo.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootTest
public class JdbcTests {

    @Autowired
    private UsersJpaRepository usersJpaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void addUser() {
        Users user = new Users();
        user.setId(1);
        user.setUserName("jim");
        usersJpaRepository.save(user);
    }

    @Test
    public void findAll() {
        List<Users> all = usersJpaRepository.findAll();
        System.out.println("all = " + all);
    }

    @Test
    void test1() {
        String sql = "select * from book ";
        List<Book> query = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<>(Book.class));
        System.out.println("query = " + query);

        List<Book> list = jdbcTemplate.queryForList(sql, Book.class);
        System.out.println("list = " + list);
    }
}
