package com.ax.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName IPController.java
 * @Description TODO
 * @createTime 2022年01月23日 14:28:00
 */

@RestController
public class IPController {

    @GetMapping("/ip1")
    public Object ip1(HttpServletRequest request) {


        Map<String, Object> map = new HashMap<>(2);
//        if (request.getHeader("x-forwarded-for") == null) {
//            map.put("ip", request.getRemoteAddr());
//        } else {
//            map.put("ip", request.getHeader("x-forwarded-for"));
//        }

        String address = null;
        try {
            address = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            address = "未获取到";
        }
        map.put("address", address);

        return map;
    }
}
