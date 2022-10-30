package com.axing;

import cn.hutool.core.io.FileUtil;
import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.document.DefaultDocumentFormatRegistry;
import org.jodconverter.core.document.DocumentFormat;
import org.jodconverter.core.office.OfficeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;

@SpringBootTest
class MainTest {

    @Test
    void test() {
        Word2PdfUtil.doc2pdf("/Users/xing/Desktop/word.docx",
                "/Users/xing/Desktop/word.pdf");
    }

    @Resource
    private DocumentConverter converter;

    @Test
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