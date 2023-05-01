package com.axing;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class MainTest {


    @SneakyThrows
    @Test
    void test1() {

        WordToPdfConverter.convert("/Users/xing/Desktop/poi笔记.docx",
                "/Users/xing/Desktop/poi笔记.pdf");

        TimeUnit.SECONDS.sleep(5);

    }


    @SneakyThrows
    @Test
    void test2() {

        Word2PdfUtil.doc2Pdf("/Users/xing/Desktop/test.docx",
                "/Users/xing/Desktop/poi笔记.pdf");
        TimeUnit.SECONDS.sleep(5);
    }
}
