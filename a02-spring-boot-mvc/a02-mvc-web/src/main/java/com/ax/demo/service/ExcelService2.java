package com.ax.demo.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.ax.demo.util.AXDateUtil;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * `
 * 导出Excel
 */
@Service
public class ExcelService2 {


    public void demoExcel(HttpServletResponse response) throws IOException {
        Map<String, Object> row1 = new LinkedHashMap<>();
        row1.put("姓名", "张三");
        row1.put("年龄", 23);
        row1.put("成绩", 88.32);
        row1.put("是否合格", true);
        row1.put("考试日期", DateUtil.date());

        Map<String, Object> row2 = new LinkedHashMap<>();
        row2.put("姓名", "李四");
        row2.put("年龄", 33);
        row2.put("成绩", 59.50);
        row2.put("是否合格", false);
        row2.put("考试日期", DateUtil.date());

        ArrayList<Map<String, Object>> rows = CollUtil.newArrayList(row1, row2);


        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
        //out为OutputStream，需要写出到的目标流

        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition", "attachment;filename=" + getEncodeFileName("测试1.xls"));
        ServletOutputStream out = response.getOutputStream();

        writer.flush(out, true);
        // 关闭writer，释放内存
        writer.close();
        //此处记得关闭输出Servlet流
        IoUtil.close(out);

    }


    public void demoExcel2(HttpServletResponse response) throws IOException {

        List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
        List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
        List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
        List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
        List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");

        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);


        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
        //out为OutputStream，需要写出到的目标流

        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition", "attachment;filename=" + getEncodeFileName("测试1.xls"));
        ServletOutputStream out = response.getOutputStream();

        writer.flush(out, true);
        // 关闭writer，释放内存
        writer.close();
        //此处记得关闭输出Servlet流
        IoUtil.close(out);

    }


    public void exportExcel(HttpServletResponse response) throws Exception {


        HSSFWorkbook workbook = new HSSFWorkbook();


        List<List<String>> all = createData();

        all = all.stream().sorted((o1, o2) -> {
            for (int i = 0; i < Math.min(o1.size(), o2.size()); i++) {

                int c = o1.get(3).compareTo(o2.get(3));
                if (c != 0) {
                    return c;
                }
            }
            return Integer.compare(o1.size(), o2.size());
        }).collect(Collectors.toList());


        all.stream().forEach((list) -> {

            HSSFSheet sheet = workbook.createSheet();
            sheet.setColumnWidth(0, 3000);
            sheet.setColumnWidth(1, 3000);


            list.stream().forEach((str) -> {


                Integer idx2 = list.indexOf(str);

                HSSFRow row = sheet.createRow(idx2);
                row.setHeightInPoints(18);
                row.createCell(0).setCellValue(str);

                HSSFCellStyle style = workbook.createCellStyle();
                HSSFFont font = workbook.createFont();

                font.setFontName("宋体");
                font.setFontHeightInPoints((short) 9.0);

//            font.setBold(true);
                style.setFont(font);
//            style.setAlignment(HorizontalAlignment.CENTER);
//            style.setVerticalAlignment(VerticalAlignment.CENTER);

                row.setRowStyle(style);
            });

        });




        /*下载的文件名称*/
        String fileName = "加油小票.xls";

        //生成excel文件
        buildExcelFile(fileName, workbook);

        //浏览器下载excel
        buildExcelDocument(fileName, workbook, response);


    }

    private List<List<String>> createData() {

        List<List<String>> all = new ArrayList<>();


        for (int i = 0; i < 100; i++) {


//            List<String> list = CollUtil.newArrayList();


            List<String> list = new ArrayList();

            list.add("           欢迎光临");
            list.add("      顾客存根");
            list.add("加油站名称：山东公司第八加油站");


            Date randomDate = AXDateUtil.randomDate("2021-03-01", "2021-08-31");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String result = format.format(randomDate);
            list.add("交易时间：" + result);


            List<Integer> numList = new ArrayList();
            numList.add(697);
            numList.add(698);
            numList.add(702);
            numList.add(710);
            numList.add(721);

            Integer idx = (int) (Math.random() * (numList.stream().count()));
            list.add("终端编号：000000000" + numList.get(idx));


            list.add("交易类型：预授权");
            list.add("枪号：" + idx);
            list.add("油品名称：0#车用柴油");

            Double nuL1 = Math.random() * 200;
            nuL1 = nuL1 + 300;
            DecimalFormat df = new DecimalFormat("#.##");
            Double nuL = Double.parseDouble(String.format("%.2f", nuL1));


            list.add("数量：" + nuL + "升");


            BigDecimal d = new BigDecimal(5.20);      //存款
            BigDecimal r = new BigDecimal(nuL);   //利息
            BigDecimal money = d.multiply(r).setScale(2, RoundingMode.HALF_EVEN);     //使用银行家算法


            list.add("应付金额：" + money);
            list.add("实收金额：" + money);
            list.add("   谢谢惠顾！欢迎下次光临");


            all.add(list);

        }

        return all;
    }


    //生成excel文件
    public void buildExcelFile(String filename, HSSFWorkbook workbook) throws Exception {

        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    //浏览器下载excel
    public void buildExcelDocument(String fileName, HSSFWorkbook workbook, HttpServletResponse response) throws Exception {


        /*火狐浏览器乱码，用这个编解码*/
//        fileName = new String(fileName.getBytes(), "ISO8859-1");


//        fileName = URLEncoder.encode(fileName, "UTF-8");

        response.reset();
        // ContentType 可以不设置
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + getEncodeFileName(fileName));

        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }


    public String getEncodeFileName(String fileName) throws UnsupportedEncodingException {

        /*火狐浏览器乱码，用这个编解码*/
        String newFileName = new String(fileName.getBytes(), "ISO8859-1");

//        fileName = URLEncoder.encode(fileName, "UTF-8");

        return newFileName;
    }
}
