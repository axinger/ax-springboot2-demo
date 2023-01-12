package com.axing.demo.dao;

import com.axing.demo.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookDAOTest {

    @Autowired
    BookDAO bookDAO;

    @Test
    void test(){
        Book book = new Book();
        book.setBookName("海底两万里");
        bookDAO.save(book);
    }
}
