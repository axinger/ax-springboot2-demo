package com.github.axinger;

import com.deepoove.poi.XWPFTemplate;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class MainTest {


    @SneakyThrows
    @Test
    void test1() {

        // 一行代码
        XWPFTemplate template = XWPFTemplate.compile("~/template.docx").render(new HashMap() {{
            put("title", "Poi-tl 模板引擎");
        }});
        template.writeToFile("out_template.docx");

    }


}
