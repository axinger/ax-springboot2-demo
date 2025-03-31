package com.github.axinger.controller;

import com.github.axinger.service.OrderPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/person")
public class OrderPersonController {

    @Autowired
    private OrderPersonService orderPersonService;

    @GetMapping("/page")
    public Object list() {
        return orderPersonService.lambdaQuery()
                .last("limit 1")
                .list();
    }
}
