package com.axing.demo.controller;

import com.axing.demo.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    private int count;

    @Autowired
    private CountService countService;

    @GetMapping("/count")
    public Object count1() {
        count++;
        return List.of(count);
    }

    @GetMapping("/count2")
    public Object count2() {
        return List.of(countService.count());
    }


    // @Operation(summary = "MTR-获取物料信息")
    @GetMapping("/b/{materialCode:.*}")
    public Object getInfoByCode(@PathVariable("materialCode") String materialCode) {
        return List.of(materialCode);
    }

}
