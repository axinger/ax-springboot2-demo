package com.axing.demo.dao;

import com.alibaba.fastjson2.JSON;
import com.axing.demo.domain.BookEntity;
import com.axing.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BookDAOTest {

    @Autowired
    BookDAO bookDAO;

    @Autowired
    BookService bookService;

    @Test
    void test_jpa_save_list() {

        String json = """
                [
                    {
                        "id": 1,
                        "bookAuthor": "jim",
                        "bookName": "海底两万里",
                        "bookPrice": 10.1
                    },
                    {
                        "id": 2,
                        "bookAuthor": "jim",
                        "bookName": "一千零一夜",
                        "bookPrice": 11.0
                    },
                    {
                        "id": 3,
                        "bookAuthor": "tom",
                        "bookName": "阿里巴巴与四十大盗",
                        "bookPrice": 12.0
                    }
                ]
                  """;

        List<BookEntity> bookEntityList = JSON.parseArray(json, BookEntity.class);
        System.out.println("bookEntityList = " + JSON.toJSONString(bookEntityList));

        // jpa 不能指定id,需要指定策略
        // List<BookEntity> bookEntityList1 = bookDAO.saveAll(bookEntityList);
        // System.out.println("save = " + JSON.toJSONString(bookEntityList1));

        // List<BookEntity> bookEntityList2 = bookDAO.saveAllAndFlush(bookEntityList);
        // System.out.println("bookEntityList2 = " + JSON.toJSONString(bookEntityList2));
        // mbp 可以insert id
        boolean save = bookService.saveBatch(bookEntityList);

    }

    @Test
    void test_jpa_save_list2() {

        String json = """
                [
                    {
                        "bookAuthor": "jim",
                        "bookName": "海底两万里",
                        "bookPrice": 10.1
                    },
                    {
                        "bookAuthor": "jim",
                        "bookName": "一千零一夜",
                        "bookPrice": 11.0
                    },
                    {
                        "bookAuthor": "tom",
                        "bookName": "阿里巴巴与四十大盗",
                        "bookPrice": 12.0
                    }
                ]
                  """;

        List<BookEntity> bookEntityList = JSON.parseArray(json, BookEntity.class);
        System.out.println("bookEntityList = " + JSON.toJSONString(bookEntityList));
        // mbp 可以insert id
        boolean save = bookService.saveBatch(bookEntityList);

    }

    @Test
    void test_mbp_list() {
        // 会报错, 因为添加了其他聚合字段
//        List<BookEntity> list1 = bookService.list();
        List<BookEntity> list1 = bookService.lambdaQuery()
                .select(BookEntity::getId,BookEntity::getBookName,BookEntity::getBookPrice)
                        .list();
        System.out.println("list1 = " + list1);
    }

    @Test
    void test_mbp_one() {
        List<BookEntity> list1 = bookService.lambdaQuery()
                .select(BookEntity::getId,BookEntity::getBookName,BookEntity::getBookPrice)
                        .last("limit 1")
                                .list();
        System.out.println("list1 = " + list1);
    }


    @Test
    void test_groupBy_count() {

        // pg 也不支持这样分组
        // 聚合与分组查询无法使用lambda表达式完成 ,需要新增字段
        // List<BookEntity> list = bookService.lambdaQuery()
        //         .groupBy(BookEntity::getBookAuthor)
        //         .list();
        // System.out.println("list = " + list);


        List<BookEntity> list = bookService.lambdaQuery()
                .select(BookEntity::getBookAuthor, BookEntity::getCount)
                .groupBy(BookEntity::getBookAuthor)

                .list();
        System.out.println("list = " + list);
    }

    @Test
    void test_groupBy_sum() {
        List<BookEntity> list = bookService.lambdaQuery()
                .select(BookEntity::getBookAuthor, BookEntity::getSumBookPrice)
                .groupBy(BookEntity::getBookAuthor)
                .list();
        System.out.println("list = " + list);
    }

    @Test
    void test_groupBy_avg() {
        List<BookEntity> list = bookService.lambdaQuery()
                .select(BookEntity::getBookAuthor, BookEntity::getAvgBookPrice)
                .groupBy(BookEntity::getBookAuthor)
                .list();
        System.out.println("list = " + list);
    }

    @Test
    void test_groupBy_min() {
        List<BookEntity> list = bookService.lambdaQuery()
                .select(BookEntity::getBookAuthor, BookEntity::getMinBookPrice)
                .groupBy(BookEntity::getBookAuthor)
                .list();
        System.out.println("list = " + list);
    }

    @Test
    void test_groupBy_max() {
        List<BookEntity> list = bookService.lambdaQuery()
                .select(BookEntity::getBookAuthor, BookEntity::getMaxBookPrice)
                .groupBy(BookEntity::getBookAuthor)
                .list();
        System.out.println("list = " + list);
    }
}
