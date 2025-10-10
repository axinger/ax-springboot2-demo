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


        String sql5 = """
                select t1.*,t2.*
                      from '/opt/123.json' t1
                      left join '/opt/1234.json' t2 on t1.name=t2.name
                """;
        List<Map<String, Object>> maps5 = jdbcTemplate.queryForList(sql5);
        System.out.println("maps5 = " + maps5);
    }

}
