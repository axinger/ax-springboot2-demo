package com.axing;

import cn.hutool.core.io.FileUtil;
import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.document.DefaultDocumentFormatRegistry;
import org.jodconverter.core.document.DocumentFormat;
import org.jodconverter.core.office.OfficeException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;

@RestController
public class Controller {


    @Resource
    private DocumentConverter converter;

    @GetMapping("/pdf")
    public void test() {
        Word2PdfUtil.doc2pdf("/Users/xing/Desktop/word.docx",
                "/Users/xing/Desktop/word.pdf");
    }

    @GetMapping("/pdf2")
    void contextLoads() {
        System.out.println("converter = " + converter);


        try {
            File file = FileUtil.file("/Users/xing/Desktop/123.docx");
            File out = FileUtil.file("/Users/xing/Desktop/123.pdf");
            final DocumentFormat targetFormat = DefaultDocumentFormatRegistry.PDF;
            converter.convert(file).to(out).as(targetFormat).execute();
        } catch (OfficeException e) {
            System.out.println("e = " + e);
        }
    }
}
