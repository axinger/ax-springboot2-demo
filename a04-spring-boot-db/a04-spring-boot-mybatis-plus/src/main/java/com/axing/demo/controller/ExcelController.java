package com.axing.demo.controller;

import com.axing.common.excel.StreamExportUtil;
import com.axing.common.excel.handler.CommonResultHandler;
import com.axing.demo.domain.BookEntity;
import com.axing.demo.mapper.BookMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ExcelController {

    @Resource
    private BookMapper bookMapper;


    @GetMapping("/utilExport")
    public void utilExport(HttpServletResponse response) throws IOException {
        System.out.println("response = " + response.getOutputStream());

        CommonResultHandler<BookEntity> resultHandler = new CommonResultHandler<>(response, BookEntity.class) {
            @Override
            public BookEntity processing(BookEntity order) {
                System.out.println("order = " + order);
                return order;
            }
        };

        bookMapper.streamSelect(resultHandler);
        StreamExportUtil.download("hello", resultHandler);
        // ExcelUtils.writeExcel(response,BookEntity.class,"123","456");


    }
}
