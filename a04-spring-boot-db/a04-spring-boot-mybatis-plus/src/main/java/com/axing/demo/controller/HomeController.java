package com.axing.demo.controller;

import com.axing.demo.domain.BookEntity;
import com.axing.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private BookService bookService;

    @GetMapping("/data")
    public Object data() {
        return List.of(1);
    }

    @GetMapping("/list")
    public Object test() {
        List<BookEntity> list = bookService.list();
        return list;
    }

}
