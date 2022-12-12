package com.ax.master.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


/**
 * @author axing
 */
@Controller
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;


    public String getErrors(Exception e) {
        StringBuilder errors = new StringBuilder();
        StackTraceElement[] stes = e.getStackTrace();
        for (StackTraceElement ste : stes) {
            errors.append(ste.getClassName());
            errors.append("\n");
        }
        return errors.toString();
    }

}
