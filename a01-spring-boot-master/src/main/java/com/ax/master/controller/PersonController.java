package com.ax.master.controller;

import com.ax.master.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName PersonController.java
 * @Description TODO
 * @createTime 2021年12月13日 16:40:00
 */

@Controller
public class PersonController {

    @Autowired
    private Person person;

    @GetMapping("/person")
    @ResponseBody
    public Object test1() {
        return person;
    }

}
