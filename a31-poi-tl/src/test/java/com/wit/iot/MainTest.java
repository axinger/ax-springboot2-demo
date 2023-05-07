package com.wit.iot;


import com.deepoove.poi.XWPFTemplate;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

class MainTest {


    @SneakyThrows
    @Test
    void test1(){

        //一行代码
        XWPFTemplate template = XWPFTemplate.compile("~/template.docx").render(new HashMap(){{
            put("title", "Poi-tl 模板引擎");
        }});
        template.writeToFile("out_template.docx");

    }


}
