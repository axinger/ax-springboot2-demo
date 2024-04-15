package com.github.axinger.controller;

import com.axing.common.excel.StreamExportUtil;
import com.axing.common.excel.handler.CommonResultHandler;
import com.axing.demo.domain.PersonEntity;
import com.axing.demo.mapper.PersonMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ExcelController {

    @Resource
    private PersonMapper personMapper;


    @GetMapping("/utilExport")
    public void utilExport(HttpServletResponse response) throws IOException {
        System.out.println("response = " + response.getOutputStream());

        CommonResultHandler<PersonEntity> resultHandler = new CommonResultHandler<>(response, PersonEntity.class) {
            @Override
            public PersonEntity processing(PersonEntity order) {
                System.out.println("order = " + order);
                return order;
            }
        };

        personMapper.streamSelect(resultHandler);
        StreamExportUtil.download("hello", resultHandler);
        // ExcelUtils.writeExcel(response,BookEntity.class,"123","456");


    }
}
