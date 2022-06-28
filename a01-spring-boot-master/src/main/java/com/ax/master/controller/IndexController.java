package com.ax.master.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /**
     * 当前版本
     */
    @Value("${version}")
    private String version;

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/")
    public String index(Model model) {
        System.out.println("version = " + version);
        model.addAttribute("appVersion", version);
        model.addAttribute("port", port);
        return "login";
    }

}
