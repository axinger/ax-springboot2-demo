package com.ax.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
