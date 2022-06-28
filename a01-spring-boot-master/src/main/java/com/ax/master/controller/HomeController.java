package com.ax.master.controller;

import com.ax.master.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    private ILoginService loginService;
    @Value("${server.port}")
    private String port;


    /**
     * PageInfo 含有页面信息
     */
    @RequestMapping(value = "/toId")
    @ResponseBody
    public Object ipLogPageInfo() {

        Map<String, Object> map = new HashMap<>();
        map.put("home", "首页");
        return map;
    }

    //    @RequiresPermissions("sys:article:list")
    @RequestMapping(value = "/home.page")
    public String homePage(Model model) {

        System.out.println("request.getParameter(\"token\") = " + request.getParameter("token"));

//        System.out.println("version = " + version);
//        ModelAndView modelAndView = new ModelAndView("home");
////        modelAndView.addObject("token","1111111");
//
//        modelAndView.addObject("version",version);
//        modelAndView.addObject("appVersion",version);
//        modelAndView.addObject("hello","abv");
//        model.addAttribute("appVersion", version);
//
        model.addAttribute("port", port);

        return "home";
    }


    @RequestMapping(value = "/home2.page")
    public ModelAndView home_html() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
//        modelAndView.addObject("token","1111111");
        return modelAndView;
    }


}
