package com.github.axinger;

import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriteConfig;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvTests {

    /**
     * 生成User列表
     *
     * @return List<User>
     */
    public static List<User> generateUserList() {
        List<User> dataList = new ArrayList<>();
        dataList.add(new User(1, "张三", "男"));
        dataList.add(new User(2, "李四", "女"));
        dataList.add(new User(3, "王五", "男"));
        dataList.add(new User(4, "", "男"));
        dataList.add(new User(5, "王五", null));
        dataList.add(new User(3, null, "男"));
        return dataList;
    }

    public CsvWriteConfig config() {


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
    public void test1() throws InterruptedException {
        File distFile = new File("C:\\Users\\xing\\Desktop\\1.csv");
        // 创建CSV写入器，使用GBK编码格式，防止出现各个CSV工具打开乱码
        CsvWriter csvWriter = CsvUtil.getWriter(distFile, CharsetUtil.CHARSET_GBK, true, config());
        csvWriter.writeHeaderLine("策略id", "账户id");
        List<List<Long>> rowList = new ArrayList<>();
        rowList.add(Arrays.asList(111L, 222L));
        rowList.add(Arrays.asList(333L, 444L));
        rowList.add(Arrays.asList(555L, 666L));
        csvWriter.write(rowList);
        csvWriter.close();


    }

    @Test
    public void test2() throws InterruptedException {
        File distFile = new File("C:\\Users\\xing\\Desktop\\2.csv");
        // 创建CSV写入器，使用GBK编码格式，防止出现各个CSV工具打开乱码
//        CsvWriter csvWriter = CsvUtil.getWriter(distFile, CharsetUtil.CHARSET_GBK, true, config());
        CsvWriter csvWriter = CsvUtil.getWriter(distFile, CharsetUtil.CHARSET_GBK, true, config());
        csvWriter.writeBeans(generateUserList());
        csvWriter.close();
    }
}
