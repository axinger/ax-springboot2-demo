package com.github.axinger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class A02B13DuckDBApplicationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void test1() {

        String sql = "SELECT * FROM read_csv(?)";
        List<Map<String, Object>> maps1 = jdbcTemplate.queryForList(sql, "/opt/123.csv");
        System.out.println("maps1 = " + maps1);


        ///  表名不能动态拼接
        List<Map<String, Object>> maps = namedParameterJdbcTemplate.queryForList(String.format("SELECT * FROM '%s' where id=:id", "/opt/123.csv"), Map.of("id", 2));
        System.out.println("maps = " + maps);

        String sql2 = """
                    select * from '/opt/123.csv'
                """;
        List<Map<String, Object>> maps2 = jdbcTemplate.queryForList(sql2);
        System.out.println("maps2 = " + maps2);

        String sql3 = """
                    select * from '/opt/123.json'
                """;
        List<Map<String, Object>> maps3 = jdbcTemplate.queryForList(sql3);
        System.out.println("maps3 = " + maps3);


        String sql4 = """
                    select * from '/opt/*.json'
                """;
        List<Map<String, Object>> maps4 = jdbcTemplate.queryForList(sql4);
        System.out.println("maps4 = " + maps4);


        /// 不能修改文件,可以读取,再写入
        String sql5 = """
                select t1.*,t2.*
                      from '/opt/123.json' t1
                      left join '/opt/1234.json' t2 on t1.name=t2.name
                """;
        List<Map<String, Object>> maps5 = jdbcTemplate.queryForList(sql5);
        System.out.println("maps5 = " + maps5);


        String sql6 = """
                    select id,
                              name,
                              age + 1 AS age,
                              'Engineer' AS job  from '/opt/123.json'
                """;
        List<Map<String, Object>> maps6 = jdbcTemplate.queryForList(sql6);
        System.out.println("maps6 = " + maps6);


        /*
            {"id":1,"name":"jim","age":11}
            {"id":2,"name":"tom","age":21}
         */
        String sql7 = """
                COPY (
                  SELECT id, name, age + 1 AS age
                  FROM '/opt/123.json'
                ) TO '/opt/new_123_1.json' WITH (FORMAT JSON, ARRAY false);
                """;
        jdbcTemplate.execute(sql7);

        /*
            [
            {"id":1,"name":"jim","age":11},
            {"id":2,"name":"tom","age":21}
            ]
         */
        String sql8 = """
                COPY (
                  SELECT id, name, age + 1 AS age
                  FROM read_json('/opt/123.json')
                ) TO '/opt/new_123_2.json' WITH (FORMAT JSON, ARRAY true);
                """;
        jdbcTemplate.execute(sql8);


        /*
        可以自定义分隔符：DELIMITER ';' 或 DELIMITER '|'
         */
        String sql9 = """
              COPY (
                SELECT id, name, age + 1 AS age
                FROM '/opt/123.csv'
              ) TO '/opt/new_123_1.csv' WITH (FORMAT CSV, HEADER true,DELIMITER '|');
              """;
        jdbcTemplate.execute(sql9);


        /*
        支持压缩：COMPRESSION GZIP（生成 .csv.gz） --- 变成16进制了
         */
        String sql10 = """
              COPY (
                SELECT id, name, age + 1 AS age
                FROM '/opt/123.csv'
              ) TO '/opt/new_123_10.csv' WITH (FORMAT CSV, HEADER true,COMPRESSION GZIP);
              """;
        jdbcTemplate.execute(sql10);


    }

}
