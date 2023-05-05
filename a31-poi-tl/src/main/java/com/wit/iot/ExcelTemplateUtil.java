package com.wit.iot;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelTemplateUtil {
    public static void main(String[] args) throws Exception {
        // 加载模板文件
        InputStream is = new FileInputStream("D:\\Users\\cepai\\Desktop\\123.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(is);

        // 定义填充数据
        Map<String, Object> params = new HashMap<>();
        params.put("name", "张三");
        params.put("age", 18);
        params.put("gender", "男");
        params.put("education", "本科");

        // 填充 Excel 模板
        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            Row row;
            Cell cell;
            for (int rowIndex = 0; rowIndex <= workbook.getSheetAt(sheetIndex).getLastRowNum(); rowIndex++) {
                row = workbook.getSheetAt(sheetIndex).getRow(rowIndex);
                if (row == null) continue;
                for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
                    cell = row.getCell(cellIndex);
                    if (cell == null || cell.getStringCellValue() == null) continue;

                    String text = cell.getStringCellValue();
                    for (Map.Entry<String, Object> entry : params.entrySet()) {
                        String key = "${" + entry.getKey() + "}";
                        if (text.contains(key)) {
                            text = text.replace(key, entry.getValue().toString());
                            cell.setCellValue(text);
                        }
                    }
                }
            }
        }

        // 输出填充后的 Excel 文件
        FileOutputStream os = new FileOutputStream("D:\\Users\\cepai\\Desktop\\123_2.xlsx");
        try {
            workbook.write(os);
            System.out.println("写入成功！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
            workbook.close();
        }
    }
}
