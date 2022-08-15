package com.axing;

import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void test() {
        Word2PdfUtil.doc2pdf("/Users/xing/Desktop/word.docx",
                "/Users/xing/Desktop/word.pdf");
    }
}