package com.axing.demo;

import com.google.common.collect.Table;
import lombok.SneakyThrows;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class PoiTests {

    @SneakyThrows
    @Test
    public void test1() {


        InputStream inputStream = new FileInputStream("D:\\Users\\cepai\\Desktop\\123.docx");
        XWPFDocument document = new XWPFDocument(inputStream);
        // 获取word中的所有段落与表格
        List<IBodyElement> elements = document.getBodyElements();
        for (IBodyElement element : elements) {
            // 段落
            if (element instanceof XWPFParagraph) {
                String text = WordRead.getParagraphText((XWPFParagraph) element);
                // System.out.println("段落text = " + text);
            }
            // 表格
            else if (element instanceof XWPFTable xwpfTable) {
                Table<Integer, Integer,String> table = WordRead.getTableText(xwpfTable);
                System.out.println("表格text = " + table);
            }
        }
    }
}
