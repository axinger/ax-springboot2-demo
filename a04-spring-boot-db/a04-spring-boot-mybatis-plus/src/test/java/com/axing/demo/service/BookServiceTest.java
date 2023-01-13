package com.axing.demo.service;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.axing.demo.domain.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;
    @Test
    void test(){
        List<BookEntity> list = bookService.list();
        System.out.println("list = " + list);

    }

    @Test
    void test_save(){
        bookService.removeById(1L);

        BookEntity book1 = new BookEntity();
        book1.setId(1L);
        book1.setBookName("海底两万里");
        bookService.save(book1);
    }

    @Test
    void test_updateById(){
        BookEntity book = bookService.getById(1);
        book.setBookName(book.getBookName()+"_v1");
        bookService.updateById(book);
    }

    @Test
    void test_saveOrUpdateBatch(){
        BookEntity book = bookService.getById(1);
        book.setBookName(book.getBookName()+"_v1");
        List<BookEntity> list = List.of(book);
        bookService.saveOrUpdateBatch(list);
    }

    @Test
    void test_saveOrUpdateBatch2(){
        BookEntity book = bookService.getById(1);
        book.setBookName(book.getBookName()+"_v1");

        BookEntity book1 = new BookEntity();
        book1.setBookName("一千零一夜");

        List<BookEntity> list = List.of(book, book1);
        bookService.saveOrUpdateBatch(list);

    }


    @Test
    void test2(){
        LocalDateTime dateTime = LocalDateTime.now().plusDays(-1);

        LocalDateTime startTime = LocalDateTimeUtil.beginOfDay(dateTime);
        LocalDateTime endTime = LocalDateTimeUtil.endOfDay(dateTime);
        List<BookEntity> list = bookService.lambdaQuery()
                .ge(BookEntity::getStartTime,startTime)
                .list();
        System.out.println("list = " + list);
    }
}
