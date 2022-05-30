package com.ax;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName IndexController.java
 * @description TODO
 * @createTime 2022年04月28日 00:22:00
 */

@RestController
@Slf4j
public class IndexController {

    @RequestMapping("/")
    public Object index() {
        final String date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS").format(LocalDateTime.now());
        log.error("error = {}", date);
        log.info("info = {}", date);
        log.debug("debug = {}", date);
        log.warn("info = {}", date);

        Map map = new HashMap();
        ;
        map.put("time", new Date());
        return map;
    }
}
