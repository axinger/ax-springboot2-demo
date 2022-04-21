package com.ax.noweb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName IndexController.java
 * @description TODO
 * @createTime 2022年04月13日 13:46:00
 */

@RestController
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    public Map index() {
        Map map = new HashMap();
        map.put("name", "jim");
        return map;
    }
}
