package com.axing.demo.dao;

import com.axing.demo.domain.BookEntity;
import com.axing.demo.model.Book;
import com.axing.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BookDAOTest {

    @Autowired
    BookDAO bookDAO;

    @Test
    void test() {
        Book book = new Book();
        book.setBookName("海底两万里");
        bookDAO.save(book);
    }

    @Autowired
    BookService bookService;

    @Test
    void test2(){
        // List<BookEntity> list1 = bookService.list();
        // System.out.println("list1 = " + list1);

        // pg 也不支持这样分组
        // List<BookEntity> list = bookService.lambdaQuery()
        //         .groupBy(BookEntity::getBookAuthor)
        //         .list();
        // System.out.println("list = " + list);
    }
}
