package com.axing.demo.service;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.axing.demo.domain.BookEntity;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    void test() {
        List<BookEntity> list = bookService.list();
        System.out.println("list = " + list);

    }

    @Test
    void test_lambdaQuery() {
        BookEntity book = new BookEntity();
        book.setBookName("海底两万里");
        List<BookEntity> list1 = bookService.lambdaQuery(book)
                .eq(BookEntity::getId, 1)
                .list();
        System.out.println("list1 = " + list1);
    }

    @Test
    void test_LambdaUtils() {
        LambdaMeta extract = LambdaUtils.extract(BookEntity::getBookName);
        System.out.println("extract.getImplMethodName() = " + extract.getImplMethodName()); // getBookName
        System.out.println("extract.getInstantiatedClass() = " + extract.getInstantiatedClass()); // class com.axing.demo.domain.BookEntity
    }

    @Test
    void test_save() {
        bookService.removeById(1L);
        BookEntity book1 = new BookEntity();
        book1.setId(1L);
        book1.setBookName("海底两万里");
        bookService.save(book1);
    }


    @Test
    void test_page() {
        Page<BookEntity> page = bookService.lambdaQuery()
                .page(Page.of(1, 2));
        System.out.println("page = " + page.getRecords());
    }


    @Test
    void test_updateById() {
        BookEntity book = bookService.getById(1);
        // book.setVersion(book.getVersion()+1);
        // book.setBookName(book.getBookName()+"_v1");
        // book.setBookName("_v1");
        book.setBookPrice(10D); // 自动+1,再set值,就无效,@Version原理如此
        bookService.updateById(book);
    }

    @Test
    void test_lambdaUpdate() {
        //  1.不会会自动填充
        BookEntity book = bookService.getById(1);
        bookService.lambdaUpdate()
                .ge(BookEntity::getId, 1)
                .set(BookEntity::getBookName, book.getBookName() + "_v1")
                .update();
    }

    @Test
    void test_lambdaUpdate1() {
        // bookService.lambdaUpdate()
        //         .eq(BookEntity::getBookAuthor, "tom")
        //         .set(BookEntity::getBookName, "一千零一夜")
        //         .update(new BookEntity());

        BookEntity book = new BookEntity();
        book.setBookAuthor("tom");
        book.setBookName("一千零一夜2");

        // bookService.saveOrUpdate(book, Wrappers.<BookEntity>lambdaQuery()
        //         .eq(BookEntity::getBookAuthor, "tom"));

        bookService.saveOrUpdate(book, bookService.lambdaQuery()
                .eq(BookEntity::getBookAuthor, "tom")
                .getWrapper());

    }

    @Test
    void test_lambdaUpdate2() {
        //  1.update(new 对象)才会自动填充
        //  2. 乐观锁失效,不支持,需要手动添加锁
        BookEntity book = bookService.getById(1);
        bookService.lambdaUpdate()
                .eq(BookEntity::getId, 1)
                .set(BookEntity::getBookName, "海底两万里")
                .update(new BookEntity());
    }

    @Test
    void test_lambdaUpdate3() {
        //  乐观锁失效,不支持,需要手动添加锁
        BookEntity book = bookService.getById(1);
        bookService.lambdaUpdate()
                .eq(BookEntity::getId, 1)
                .eq(BookEntity::getVersion, book.getVersion())
                .set(BookEntity::getBookName, "海底两万里")
                .set(BookEntity::getVersion, book.getVersion() + 1)
                .update(new BookEntity());
    }

    @Test
    void test_saveOrUpdateBatch() {
        BookEntity book = bookService.getById(1);
        book.setBookName(book.getBookName() + "_v1");
        List<BookEntity> list = List.of(book);
        bookService.saveOrUpdateBatch(list);
    }

    @Test
    void test_saveOrUpdateBatch2() {
        BookEntity book = bookService.getById(1);
        book.setBookName(book.getBookName() + "_v1");

        BookEntity book1 = new BookEntity();
        book1.setBookName("一千零一夜");

        List<BookEntity> list = List.of(book, book1);
        bookService.saveOrUpdateBatch(list);

    }


    @Test
    void test2() {
        LocalDateTime dateTime = LocalDateTime.now().plusDays(-1);

        LocalDateTime startTime = LocalDateTimeUtil.beginOfDay(dateTime);
        LocalDateTime endTime = LocalDateTimeUtil.endOfDay(dateTime);
        List<BookEntity> list = bookService.lambdaQuery()
                .ge(BookEntity::getStartTime, startTime)
                .list();
        System.out.println("list = " + list);
    }
}
