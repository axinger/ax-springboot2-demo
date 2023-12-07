package com.github.axinger;

import com.github.axinger.domain.TPurPoorder;
import com.github.axinger.service.TPurPoorderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisFlexSqlserverApplicationTest {

    @Autowired
    private TPurPoorderService tPurPoorderService;

    @Test
    void test1() {
        TPurPoorder ome = tPurPoorderService.getById(243211);
        System.out.println("ome = " + ome);
    }

}
