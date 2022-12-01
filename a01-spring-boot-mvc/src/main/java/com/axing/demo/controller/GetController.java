package com.axing.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class GetController {

    @RequestMapping("/request")
    public Object RequestMapping(String id) {
        return List.of(id);
    }

    @GetMapping("/get")
    public Object GetMapping(String id) {
        return List.of(id);
    }

    @PostMapping("/post")
    public Object PostMapping(@RequestBody Map map) {
        System.out.println("map = " + map);
        return map;
    }

    @PutMapping("/put")
    public Object PutMapping(String id) {
        return List.of(id);
    }


    @DeleteMapping("/delete")
    public Object DeleteMapping(String id) {
        return List.of(id);
    }

    @PatchMapping("/patch")
    public Object PatchMapping(String id) {
        return List.of(id);
    }
}
