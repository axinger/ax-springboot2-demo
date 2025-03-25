package com.github.axinger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/test1")
    public Object count1() {
        Map<String, Object> map = new HashMap<>();
        map.put("date", LocalDate.now());
        map.put("date2", LocalDateTime.now());
        map.put("date3", LocalTime.now());
        map.put("date4", ZonedDateTime.now());
        return map;
    }

}
