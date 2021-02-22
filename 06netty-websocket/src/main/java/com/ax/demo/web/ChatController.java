package com.ax.demo.web;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.ax.demo.module.UserInfo;
import com.ax.demo.service.ChatConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/chat")
@Slf4j
public class ChatController {

	// 跳转到交谈聊天页面
	@RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView index(String token) {
        ModelAndView mav = new ModelAndView("single_chat");
        mav.addObject("uid", token);
        return mav;
    }
}
