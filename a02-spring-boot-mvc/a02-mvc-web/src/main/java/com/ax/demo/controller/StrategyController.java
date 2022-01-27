package com.ax.demo.controller;

import com.ax.demo.strategy.SimpleContext;
import com.ax.demo.strategy2.StrategyFactory;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName StrategyController.java
 * @Description TODO
 * @createTime 2022年02月13日 13:42:00
 */
@RestController
@Api(tags = "策略模式")
public class StrategyController {

    @Autowired
    private SimpleContext simpleContext;

    @Autowired
    private StrategyFactory strategyFactory;

    @GetMapping("/strategy")
    public Map choose(String type){
        final String name = strategyFactory.run(type).name();
        System.out.println("name = " + name);
        Map map = new HashMap();
        map.put("name",name);
        return map;
    }

}
