package com.ax.demo.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.ax.demo.config.publish.PublishSender;
import com.ax.demo.config.routing.RoutingSender;
import com.ax.demo.config.simple.SimpleSender;
import com.ax.demo.config.top.TopicSender;
import com.ax.demo.config.work.WorkSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by macro on 2020/5/19.
 */
@Controller
public class RabbitController {

    @Autowired
    private SimpleSender simpleSender;
    @Autowired
    private WorkSender workSender;
    @Autowired
    private PublishSender publishSender;
    @Autowired
    private RoutingSender routingSender;
    @Autowired
    private TopicSender topicSender;

    @GetMapping(value = "/")
    @ResponseBody
    public Object index() {
        Map map = new HashMap<String, Object>(3);
        map.put("success", true);
        map.put("data", "index");
        return map;
    }


    //@ApiOperation("1.简单模式")
    @GetMapping(value = "/simple")
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

    //@ApiOperation("2.工作模式")
    @GetMapping(value = "/work")
    @ResponseBody
    public Object workTest() {
        for (int i = 0; i < 11; i++) {
            workSender.send(i);
            ThreadUtil.sleep(1000);
        }
        Map map = new HashMap<String, Object>(3);
        map.put("success", true);

        return map;
    }

    //@ApiOperation("3.发布/订阅模式")
    @GetMapping(value = "/fanout")
    @ResponseBody
    public Object fanoutTest() {
        for (int i = 0; i < 12; i++) {
            publishSender.send(i);
            ThreadUtil.sleep(1000);
        }
        Map map = new HashMap<String, Object>(3);
        map.put("success", true);

        return map;
    }

    //@ApiOperation("4.路由模式")
    @GetMapping(value = "/direct")
    @ResponseBody
    public Object directTest() {
        for (int i = 0; i < 13; i++) {
            routingSender.send(i);
            ThreadUtil.sleep(1000);
        }
        Map map = new HashMap<String, Object>(3);
        map.put("success", true);

        return map;
    }

    //@ApiOperation("5.主题模式")
    @GetMapping(value = "/topic")
    @ResponseBody
    public Object topicTest() {
        for (int i = 0; i < 14; i++) {
            topicSender.send(i);
            ThreadUtil.sleep(1000);
        }
        Map map = new HashMap<String, Object>(3);
        map.put("success", true);

        return map;
    }
}
