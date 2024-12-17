package com.github.axinger;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriteConfig;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CsvTests {

    /**
     * 生成User列表
     * @return List<User>
     */
    public static List<User> generateUserList(){
        List<User> dataList = new ArrayList<>();
        dataList.add(new User(1, "张三", "男"));
        dataList.add(new User(2, "李四", "女"));
        dataList.add(new User(3, "王五", "男"));
        dataList.add(new User(4, "", "男"));
        dataList.add(new User(5, "王五", null));
        dataList.add(new User(3, null, "男"));
        return dataList;
    }

    public CsvWriteConfig config(){


        CsvWriteConfig csvWriteConfig = new CsvWriteConfig();
        //设置 文本分隔符，文本包装符，默认双引号'"'
        // csvWriteConfig.setTextDelimiter('\t');
        // 字段分割符号，默认为逗号
//        csvWriteConfig.setFieldSeparator('|');
        // 设置注释符号
        // csvWriteConfig.setCommentCharacter('#');
        // 设置是否始终使用文本分隔符，文本包装符，默认false
        // csvWriteConfig.setAlwaysDelimitText(true);
        // 换行符默认为CharUtil.CR, CharUtil.LF
        // csvWriteConfig.setLineDelimiter(lineDelimiter);
        // 设置标题行的别名,如果不设置则表头为id,name,gende

        return csvWriteConfig;

    }

    @Test
    void test01() throws IOException {

        File distFile = new File(System.getProperty("java.io.tmpdir"), "test.csv");

        System.out.println("distFile.getAbsolutePath() = " + distFile.getAbsolutePath());
    }
    /**
     * 使用自定CSV配置生成CSV文件
     */
    @Test
    public  void test1() throws InterruptedException {
        // 可以通过设置FileWriter的编码来控制输出文件的编码格式

        // 写到临时目录，如果临时目录满了系统会自动清除。
//        File distFile = new File(System.getProperty("C:\\Users\\xing\\Desktop"), "test.csv");

        File distFile = new File("C:\\Users\\xing\\Desktop\\hutoolCsv.csv");

        // 创建CSV写入器，使用GBK编码格式，防止出现各个CSV工具打开乱码
//        CsvWriter csvWriter = new CsvWriter(distFile, CharsetUtil.CHARSET_GBK,true,config);
        CsvWriter csvWriter =  CsvUtil.getWriter(distFile, CharsetUtil.CHARSET_UTF_8,true,config());
//        CsvWriter csvWriter =  CsvUtil.getWriter(distFile);

//        CsvWriter csvWriter =  CsvUtil.getWriter("C:\\Users\\xing\\Desktop\\hutoolCsv.csv" , CharsetUtil.CHARSET_UTF_8,true);



        csvWriter.writeHeaderLine("策略id", "账户id");
        List<List<Long>> rowList = new ArrayList<>();
        rowList.add(Arrays.asList(111L, 222L));
        rowList.add(Arrays.asList(333L, 444L));
        rowList.add(Arrays.asList(555L, 666L));
        csvWriter.write(rowList);
        csvWriter.close();


    }


//
//    @Test
//    void testCsv() {
//
//        // 构造，覆盖已有文件（如果存在），默认编码UTF-8
//        File hutoolCsv = new File("hutoolCsv1.csv");
//        CsvWriter writer = new CsvWriter(hutoolCsv);
//        // 设置编码
//        // CsvWriter writer = new CsvWriter(hutoolCsv, StandardCharsets.UTF_8);
//        writer.writeBeans(users);
//        writer.flush();
//        writer.close();
//    }
}
