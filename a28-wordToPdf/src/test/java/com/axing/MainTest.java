package com.axing;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import org.junit.jupiter.api.Test;

import java.io.*;


class MainTest {
    @Test
    void test1() {
        System.out.println(" = " + "1234".replaceFirst("123", ""));

    }

    @Test
    void test() {

        File inputWord = new File("C:\\Users\\xing\\Desktop\\硬度检测报告.docx");
        File outputFile = new File("C:\\Users\\xing\\Desktop\\12.pdf");
        try {
            InputStream docxInputStream = new FileInputStream(inputWord);
            OutputStream outputStream = new FileOutputStream(outputFile);
            IConverter converter = LocalConverter.builder().build();
            converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
            outputStream.close();
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}