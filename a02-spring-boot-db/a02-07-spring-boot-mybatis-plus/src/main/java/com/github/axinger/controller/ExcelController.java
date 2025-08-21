package com.github.axinger.controller;

import com.axing.common.excel.StreamExportUtil;
import com.axing.common.excel.handler.CommonResultHandler;
import com.github.axinger.domain.SysPersonEntity;
import com.github.axinger.mapper.SysPersonMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ExcelController {

    @Resource
    private SysPersonMapper sysPersonMapper;


    @GetMapping("/utilExport")
    public void utilExport(HttpServletResponse response) throws IOException {
        System.out.println("response = " + response.getOutputStream());

        CommonResultHandler<SysPersonEntity> resultHandler = new CommonResultHandler<>(response, SysPersonEntity.class) {
            @Override
            public SysPersonEntity processing(SysPersonEntity order) {
                System.out.println("order = " + order);
                return order;
            }
        };

        sysPersonMapper.selectStream(resultHandler);
        StreamExportUtil.download("hello", resultHandler);
        // ExcelUtils.writeExcel(response,BookEntity.class,"123","456");


    }
}
