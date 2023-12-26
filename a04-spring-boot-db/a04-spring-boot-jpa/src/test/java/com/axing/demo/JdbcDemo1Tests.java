package com.axing.demo;

import com.axing.demo.dao.BookDAO;
import com.axing.demo.dao.UsersJpaRepository;
import com.axing.demo.model.Book;
import com.axing.demo.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class JdbcDemo1Tests {

    // JpaRepository
    @Autowired
    private UsersJpaRepository usersJpaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // JpaRepository
    @Autowired
    private BookDAO bookDAO;

    @Test
    void test_book_save() {
        Book book = new Book();
        book.setId(1L);
        book.setBookName("海底两万里");
        LocalDateTime dateTime = LocalDateTime.now().plusDays(-1);
        book.setStartTime(dateTime);
        book.setEndTime(dateTime.plusHours(2));
        bookDAO.save(book);
    }

    @Test
    void test_book_save2() {
        Book book = new Book();
        book.setBookName("十万个为什么");
        LocalDateTime dateTime = LocalDateTime.now().plusDays(-1);
        book.setStartTime(dateTime);
        book.setEndTime(dateTime.plusHours(2));
        bookDAO.save(book);
    }

    @Test
    void test_book_saveAndFlush() {
        Book book = new Book();
        book.setId(1L);
        book.setBookName("海底两万里2");
        LocalDateTime dateTime = LocalDateTime.now().plusDays(-1);
        book.setStartTime(dateTime);
        book.setEndTime(dateTime.plusHours(2));
        bookDAO.saveAndFlush(book);
    }


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
