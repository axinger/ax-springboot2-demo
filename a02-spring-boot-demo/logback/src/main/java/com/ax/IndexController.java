package com.ax;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Object index(){

        log.error("error");
        log.info("info");
        log.debug("debug");
        log.warn("info");

        Map map = new HashMap();;
        map.put("time",new Date());
        return map;
    }
}
