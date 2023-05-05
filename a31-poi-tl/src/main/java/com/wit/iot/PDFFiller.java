package com.wit.iot;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.*;
import java.util.Map;

public class PDFFiller {
    public static void main(String[] args) throws Exception {
        InputStream is = new FileInputStream("D:\\Users\\cepai\\Desktop\\123.pdf");
        OutputStream os = new FileOutputStream("D:\\Users\\cepai\\Desktop\\123_2.pdf");

        // Load the PDF template
        PdfReader reader = new PdfReader(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader, baos);

        // Find placeholders in the PDF document and replace them with content
        AcroFields fields = stamper.getAcroFields();
        fields.setField("name", "123");
        fields.setFieldProperty("name", "textcolor", BaseColor.RED, null);


//         // 获取PDF表单字段
//         AcroFields form = stamper.getAcroFields();
//         Map<String, AcroFields.Item> fields = form.getFields();
//
// // 循环遍历字段并设置字体和颜色
//         for (String key : fields.keySet()) {
//             // 获取占位符
//             String placeholder = form.getField(key);
//             System.out.println("placeholder = " + placeholder);
//             if (placeholder != null && placeholder.contains("name")) {
//                 // 替换占位符为实际文本
//                 String text = "Hello World";
//                 String fieldContent = placeholder.replace("name", text);
//
//                 // 设置字段属性
//                 form.setFieldProperty(key, "textcolor", BaseColor.BLUE, null);
//                 form.setFieldProperty(key, "textfont", BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), null);
//                 form.setField(key, fieldContent);
//             }
//         }

        // Write changes to output file
        stamper.close();
        baos.writeTo(os);

        is.close();
        os.close();


    }
}
