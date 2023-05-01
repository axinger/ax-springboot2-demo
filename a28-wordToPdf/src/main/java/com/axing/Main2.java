package com.axing;

import com.deepoove.poi.XWPFTemplate;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Main2 {
    /**
     * 将word模板转化成pdf
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Map datas = new HashMap();
        datas.put("title","我是活动标题");
        XWPFTemplate template = XWPFTemplate.compile("/Users/xing/Desktop/test.docx");
        template.render(datas);
        FileOutputStream out = new FileOutputStream("/Users/xing/Desktop/test1.docx");
        template.write(out);
        template.close();
        out.close();


//        Map<String,Object> datas = new HashMap<>();
//        datas.put("title", "我是活动标题"); //给{{title}}标识赋值
//        XWPFTemplate template = XWPFTemplate.compile("/Users/xing/Desktop/test.docx");
//        template.render(datas);
////将word转成pdf
//        PdfOptions options = PdfOptions.create();
//        OutputStream outPDF = Files.newOutputStream(Paths.get("/Users/xing/Desktop/tes2.pdf"));
//
//        try  {
//            PdfConverter.getInstance().convert(template.getXWPFDocument(), outPDF, options);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//
//            template.close();
//            outPDF.close();
//            outPDF.flush(); // 刷新缓冲区
//            outPDF.close(); // 关闭流
//        }

    }
}
