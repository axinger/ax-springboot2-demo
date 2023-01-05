package com.axing.demo;

import com.axing.demo.dao.*;
import com.axing.demo.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJdbcTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class JdbcTests {

    @Autowired
    private UsersJpaRepository usersJpaRepository;

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
}
