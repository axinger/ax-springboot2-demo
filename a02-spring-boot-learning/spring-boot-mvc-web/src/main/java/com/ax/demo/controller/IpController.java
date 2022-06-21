package com.ax.demo.controller;

import cn.hutool.core.lang.func.LambdaUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.time.LocalDateTime;
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
public class IpController {

    @GetMapping("/ip1")
    public Object ip1(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>(2);
        map.put(LambdaUtil.getFieldName(HttpServletRequest::getScheme), request.getScheme());
        map.put(LambdaUtil.getFieldName(HttpServletRequest::getServerName), request.getServerName());
        map.put(LambdaUtil.getFieldName(HttpServletRequest::getServerPort), request.getServerPort());
        map.put(LambdaUtil.getFieldName(HttpServletRequest::getContextPath), request.getContextPath());

        String remoteAddr;

        if (request.getHeader("x-forwarded-for") == null) {
            remoteAddr = request.getRemoteAddr();
        } else {
            remoteAddr = request.getHeader("x-forwarded-for");
        }

        map.put(LambdaUtil.getFieldName(HttpServletRequest::getContextPath), remoteAddr);

        String address;
        try {
            address = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            address = "未获取到";
        }
        map.put(LambdaUtil.getFieldName(InetAddress::getHostAddress), address);
        map.put("localDateTime", LocalDateTime.now());

        return map;
    }
}
