package com.ax.demo.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.ax.demo.config.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by macro on 2020/5/19.
 */
@Api(tags = "RabbitController", description = "RabbitMQ功能测试")
@Controller
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private SimpleSender simpleSender;
    @Autowired
    private WorkSender workSender;
    @Autowired
    private FanoutSender fanoutSender;
    @Autowired
    private DirectSender directSender;
    @Autowired
    private TopicSender topicSender;

    @ApiOperation("简单模式")
    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    @ResponseBody
    public Object simpleTest() {
        for (int i = 0; i < 10; i++) {
            simpleSender.send();
            ThreadUtil.sleep(1000);
        }
        Map map = new HashMap<String, Object>(3);
        map.put("success", true);

        return map;
    }

    @ApiOperation("工作模式")
    @RequestMapping(value = "/work", method = RequestMethod.GET)
    @ResponseBody
    public Object workTest() {
        for (int i = 0; i < 10; i++) {
            workSender.send(i);
            ThreadUtil.sleep(1000);
        }
        Map map = new HashMap<String, Object>(3);
        map.put("success", true);

        return map;
    }

    @ApiOperation("发布/订阅模式")
    @RequestMapping(value = "/fanout", method = RequestMethod.GET)
    @ResponseBody
    public Object fanoutTest() {
        for (int i = 0; i < 10; i++) {
            fanoutSender.send(i);
            ThreadUtil.sleep(1000);
        }
        Map map = new HashMap<String, Object>(3);
        map.put("success", true);

        return map;
    }

    @ApiOperation("路由模式")
    @RequestMapping(value = "/direct", method = RequestMethod.GET)
    @ResponseBody
    public Object directTest() {
        for (int i = 0; i < 11; i++) {
            directSender.send(i);
            ThreadUtil.sleep(1000);
        }
        Map map = new HashMap<String, Object>(3);
        map.put("success", true);

        return map;
    }

    @ApiOperation("通配符模式")
    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    @ResponseBody
    public Object topicTest() {
        for (int i = 0; i < 10; i++) {
            topicSender.send(i);
            ThreadUtil.sleep(1000);
        }
        Map map = new HashMap<String, Object>(3);
        map.put("success", true);

        return map;
    }
}