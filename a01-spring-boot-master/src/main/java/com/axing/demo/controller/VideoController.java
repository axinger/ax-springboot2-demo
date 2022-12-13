package com.axing.demo.controller;

import com.axing.demo.handler.MyResourceHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName VideoController.java
 * @Description TODO
 * @createTime 2021年12月27日 16:07:00
 */
@Controller
public class VideoController {


    @Autowired
    private MyResourceHandler myResourceHandler;


    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public void video(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        try {
            String path = "D:/abc.mp4";
            File file = new File(path);
            if (file.exists()) {
                request.setAttribute(MyResourceHandler.ATTR_FILE, path);
                myResourceHandler.handleRequest(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            }
        } catch (Exception e) {

        }
    }

}
