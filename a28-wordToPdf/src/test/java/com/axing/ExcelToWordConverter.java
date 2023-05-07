package com.axing;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import lombok.SneakyThrows;

import com.aspose.cells.Workbook;
import com.aspose.cells.FileFormatType;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.Optional;

public class ExcelToWordConverter {

    @SneakyThrows
    @Test
    void test1() {

        String wordPath = "D:\\Users\\cepai\\Desktop\\123.xlsx";

        String pdfPath = "D:\\Users\\cepai\\Desktop\\123_word.docx";

        // 加载Excel文件并创建 Workbook 对象
        FileInputStream fis = new FileInputStream(wordPath);
        Workbook workbook = new Workbook(fis);

        // 获取工作簿的第一个工作表
        com.aspose.cells.Worksheet sheet = workbook.getWorksheets().get(0);

        // 加载 Aspose.Words 文档对象
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);

        // 将 Excel 表格插入到 Word 文档中
        builder.startTable();
        for (int i = 0; i < sheet.getCells().getMaxRow() + 1; i++) {
            builder.insertCell();
            String string = Optional.ofNullable(sheet.getCells().get(i, 0).getValue()).orElse("").toString();

            builder.write(string);
            builder.insertCell();

            String string1 = Optional.ofNullable(sheet.getCells().get(i, 1).getValue()).orElse("").toString();
            builder.write(string1);
            builder.endRow();
        }
        builder.endTable();

        // 将 Word 文档保存为 DOCX 格式
        doc.save(pdfPath);

    }

}
