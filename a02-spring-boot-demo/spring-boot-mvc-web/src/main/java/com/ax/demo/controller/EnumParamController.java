package com.ax.demo.controller;

import com.ax.demo.paramenum.GenderEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName EnumParamController.java
 * @Description TODO
 * @createTime 2022年02月13日 15:18:00
 */
@RestController
public class EnumParamController {

    /**
     * url 可以传值 MALE 或者 FEMALE 字串类型,也可以传值0,1对应的值
     */
    @GetMapping("/enumParam")
    public Map choose(GenderEnum gender) {
        System.out.println("gender = " + gender);

        Map map = new HashMap();
        map.put("gender", gender);
        return map;
    }
}
