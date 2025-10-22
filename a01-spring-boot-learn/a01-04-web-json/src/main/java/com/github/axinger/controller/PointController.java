package com.github.axinger.controller;

import com.github.axinger.entity.Point;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/point")
public class PointController {


    @PostMapping(value = "/1")
    public Object json(@RequestBody Point point) {
        System.out.println("point = " + point);
        return point;
    }
}
