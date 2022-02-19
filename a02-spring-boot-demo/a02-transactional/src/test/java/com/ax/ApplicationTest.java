package com.ax;


import com.ax.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ApplicationTest {


//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Autowired
    UserService userService;

    /**
     * 获取示例数据库 t_user 的全部信息
     *
     * @return 返回 json 数据
     */
    @Test
    public void test1() {
//        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from t_user ");
        List<Map<String, Object>> list = userService.getAll();
        System.out.println("list = " + list);
    }


}
