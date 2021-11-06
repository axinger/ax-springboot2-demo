package com.ax.demo.controller;

import com.ax.demo.service.ExcelService;
import com.ax.demo.service.ExcelService2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j

@RequestMapping(value = "/excel")
public class ExcelController {

    @Resource
    ExcelService excelService;

    @Resource
    ExcelService2 excelService2;


    @RequestMapping(value = "/excel")
    public void excel(HttpServletResponse response) throws Exception {
        excelService.exportExcel(response);
    }

    @RequestMapping(value = "/excel2")
    public void excel2(HttpServletResponse response) throws Exception {
        excelService2.exportExcel(response);
    }

    @RequestMapping(value = "/demo1")
    public void demo1(HttpServletResponse response) throws Exception {
        excelService2.demoExcel(response);
    }

    @RequestMapping(value = "/demo2")
    public void demo2(HttpServletResponse response) throws Exception {
        excelService2.demoExcel2(response);
    }
}
