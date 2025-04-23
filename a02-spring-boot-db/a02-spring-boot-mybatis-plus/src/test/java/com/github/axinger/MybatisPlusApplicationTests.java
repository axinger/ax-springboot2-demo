package com.github.axinger;

import com.baomidou.mybatisplus.core.toolkit.AES;
import com.github.axinger.domain.SysPersonEntity;
import com.github.axinger.domain.SysUserTotalEntity;
import com.github.axinger.mapper.DynamicQueryMapper;
import com.github.axinger.service.SysPersonService;
import com.github.axinger.service.SysUserTotalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//@SpringBootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MybatisPlusApplicationTests {

    String randomKey = "abcdef1234567890";
    @Autowired
    private SysPersonService sysPersonService;
    @Autowired
    private SysUserTotalService sysUserTotalService;

    //    // Jar 启动参数（ idea 设置 Program arguments , 服务器可以设置为启动环境变量 ）
//--mpw.key=d1104d7c3b616f0b
    @Test
    void test1_username() {
        // 生成 16 位随机 AES 密钥
//        String randomKey = AES.generateRandomKey();

        System.out.println("密钥：" + randomKey);

        String data = "root";
        System.out.println("加密前：" + data);

        // 密钥加密
        String result = AES.encrypt(data, randomKey);
        System.out.println("加密后：" + result);

        System.out.println("解密后：" + AES.decrypt(result, randomKey));
    }

    @Test
    void test1_pwd() {
        // 生成16位密钥
        System.out.println("密钥：" + randomKey);

        String data = "123456";
        System.out.println("加密前：" + data);

        // 密钥加密
        String result = AES.encrypt(data, randomKey);
        System.out.println("加密后：" + result);

        System.out.println("解密后：" + AES.decrypt(result, randomKey));
    }

    @Test
    void test2_username() {

        SysUserTotalEntity one = sysUserTotalService.getById(1);

        System.out.println("one = " + one);
    }

    @Test
    void test2_pwd() {

        List<SysUserTotalEntity> list = sysUserTotalService.lambdaQuery().list();
        System.out.println("list = " + list);
    }


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void test3() {
        String url = "/person/add";
        String result = testRestTemplate.getForObject(url, String.class);
        System.out.println("result = " + result);
    }

    @Test
    void test4() {
        ParameterizedTypeReference<List<SysPersonEntity>> type = new ParameterizedTypeReference<>() {
        };

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("token", "jim");

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        String url = "/person/list";
        ResponseEntity<List<SysPersonEntity>> exchange = testRestTemplate.exchange(url, HttpMethod.GET, httpEntity, type);
        System.out.println("exchange = " + exchange.getBody());
    }


    @Autowired
    private DynamicQueryMapper dynamicQueryMapper;

    @Test
    void test5() {

        String sql = "select a,c,b,d from sys_alphabet";

        //data = [{a=1, c=3, b=2, d=4}, {a=11, c=31, b=21, d=41}]
        List<LinkedHashMap<String, Object>> data = dynamicQueryMapper.queryTableLinkedHashMap(sql);
        System.out.println("data = " + data);

        // data1 = [{a=null, c=3, b=2, d=4}, {a=null, c=31, b=21, d=41}]
        List<Map<String, Object>> data1 = dynamicQueryMapper.queryTableLinkedHashMap2(sql);
        System.out.println("data1 = " + data1);

        //data2 = [{a=1, b=2, c=3, d=4}, {a=11, b=21, c=31, d=41}]
        List<TreeMap<String, Object>> data2 = dynamicQueryMapper.queryTableTreeMap(sql);
        System.out.println("data2 = " + data2);

        //data3 = [{a=1, b=2, c=3, d=4}, {a=11, b=21, c=31, d=41}]
        List<Map<String, Object>> data3 = dynamicQueryMapper.queryTableMap(sql);
        System.out.println("data3 = " + data3);
    }

    @Test
    void test51() {

        String sql = "select * from sys_alphabet";

        // 不指定,就按照表字段顺序
        // data = [{id=1, b=2, c=3, d=4, a=1}, {id=2, b=21, c=31, d=41, a=11}],
        List<LinkedHashMap<String, Object>> data = dynamicQueryMapper.queryTableLinkedHashMap(sql);
        System.out.println("data = " + data);

        // data1 = [{id=1, b=2, c=3, d=4, a=1}, {id=2, b=21, c=31, d=41, a=11}]
        List<Map<String, Object>> data1 = dynamicQueryMapper.queryTableLinkedHashMap2(sql);
        System.out.println("data1 = " + data1);


        // data2 = [{a=1, b=2, c=3, d=4, id=1}, {a=11, b=21, c=31, d=41, id=2}]
        List<TreeMap<String, Object>> data2 = dynamicQueryMapper.queryTableTreeMap(sql);
        System.out.println("data2 = " + data2);

        // data3 = [{a=1, b=2, c=3, d=4, id=1}, {a=11, b=21, c=31, d=41, id=2}]
        List<Map<String, Object>> data3 = dynamicQueryMapper.queryTableMap(sql);
        System.out.println("data3 = " + data3);
    }

    @Test
    void test6() {

        //[java.lang.IllegalArgumentException: SQL 不能为空] ,进行校验
//        String sql = "";
        String sql = "delete from sys_alphabet";
        List<Map<String, Object>> data = dynamicQueryMapper.queryTableMap(sql);
        System.out.println("data = " + data);
    }

    @Test
    void test7() {

        // 没有进行校验
//        String sql = "";
        String sql = "select a,c,b,d from sys_alphabet";
        List<Map<String, Object>> data = dynamicQueryMapper.queryTableMap2(sql, "id");
        System.out.println("data = " + data);
    }

    @Test
    void test8() {

        //[java.lang.IllegalArgumentException: SQL 不能为空] ,进行校验
//        String sql = "";
        String sql = "select a,c,b,d from sys_alphabet";
        List<LinkedHashMap<String, Object>> data = dynamicQueryMapper.queryTableHasId(sql, 1);
        System.out.println("data = " + data);

        List<LinkedHashMap<String, Object>> data2 = dynamicQueryMapper.queryTableHasId(sql, null);
        System.out.println("data2 = " + data2);
    }
}
