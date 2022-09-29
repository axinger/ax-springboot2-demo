package com.axing.controller;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class PdfController {

    @RequestMapping(value = "pdf",method = RequestMethod.POST)
    public String exportPdf( HttpServletResponse response) throws UnsupportedEncodingException {
        // 1.指定解析器
        System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
                "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
        String filename="车辆维修审批单.pdf";
        String path="e:/";
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + URLEncoder.encode(filename, "UTF-8"));
        OutputStream os = null;
        PdfStamper ps = null;
        PdfReader reader = null;
        try {
            os = response.getOutputStream();
            // 2 读入pdf表单
            reader = new PdfReader(path+ "/"+filename);
            // 3 根据表单生成一个新的pdf
            ps = new PdfStamper(reader, os);
            // 4 获取pdf表单
            AcroFields form = ps.getAcroFields();
            // 5给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示
            BaseFont bf = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1",
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            form.addSubstitutionFont(bf);
            // 6查询数据================================================
            Map<String, String> data = new HashMap<String, String>();
            data.put("commitTime", LocalDate.now().toString());
            data.put("driver", "1");
            data.put("driver", "1");
            // 7遍历data 给pdf表单表格赋值
            for (String key : data.keySet()) {
                form.setField(key,data.get(key).toString());
            }
            ps.setFormFlattening(true);
            log.info("*******************PDF导出成功***********************");
        } catch (Exception e) {
            log.error("*******************PDF导出失败***********************");
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                reader.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
