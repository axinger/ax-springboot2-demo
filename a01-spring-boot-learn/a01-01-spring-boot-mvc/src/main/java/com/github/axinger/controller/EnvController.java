package com.github.axinger.controller;

import com.github.axinger.model.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/env")
public class EnvController {
    @Autowired
    private ApplicationInfo applicationInfo;


    @Autowired
    private AxingerUserProperties axingerUserProperties;

    @Autowired
    private AxingerPersonProperties axingerPersonProperties;

    @Resource
    private UserProperties userProperties;

    @Resource
    MyYmlBean myYmlBean;

    @GetMapping("/1")
    public Object test2() {
        return applicationInfo;
    }

    @GetMapping("/2")
    public Object PostMapping() {
        Map<String, Object> map = new HashMap<>();
//        map.put("axingerUserProperties", axingerUserProperties.all());
        // record 可以直接返回
        map.put("axingerUserProperties", axingerUserProperties);
        // class 不要单独使用 @Configuration  ,不然无整体获取,但可以属性一个个获取, 统一使用 EnableConfigurationProperties
//        map.put("person", axingerPersonProperties); // 不可用
        map.put("person", axingerPersonProperties.getName()); // 可以
        map.put("userProperties", userProperties);
        map.put("myYmlBean", myYmlBean);
        return map;
    }
}
