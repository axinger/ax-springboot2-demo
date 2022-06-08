package com.ax.demo.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/***、
 *通过Spring将实现Strategy的实现类都自动注入到strategyMap类中，
 * 当用户传入选择的资源池时，自动根据资源池id去对应的资源池实现中查找资源。
 *
 */
@Service
public class SimpleContext {
    // 使用注解 会用 @Component("C") 属性名称作为key,对象作为value
    @Autowired
    private final Map<String, Strategy> strategyMap = new ConcurrentHashMap<>();

    public SimpleContext(Map<String, Strategy> strategyMap) {
        this.strategyMap.clear();
        // 会用 @Component("C") 属性名称作为key,对象作为value
//        System.out.println("strategyMap = " + strategyMap);
//        strategyMap.forEach((k, v)-> this.strategyMap.put(k, v));
    }

    public String getResource(DemoPojo demoPojo) {
        return strategyMap.get(demoPojo.getPoolid()).getType(demoPojo);
    }
}
