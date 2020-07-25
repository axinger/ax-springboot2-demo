package com.ax.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/t2")
public class TestController2 {

    @RequestMapping(value = "/test1")
    @ResponseBody
    public String test1(@RequestParam String name) {

        return name;
    }


    @RequestMapping(value = "/test2")
    public String test2(@RequestParam String name) {

        return name;
    }

}
