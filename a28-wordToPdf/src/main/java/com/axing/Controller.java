package com.axing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    @GetMapping("/pdf")
    public void test() {
        Word2PdfUtil.doc2pdf("/Users/xing/Desktop/word.docx",
                "/Users/xing/Desktop/word.pdf");
    }
}
