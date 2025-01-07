package com.github.axinger;

import com.github.axinger.db1.domain.SysPersonEntity;
import com.github.axinger.db1.service.SysPersonService;
import com.github.axinger.db2.domain.SysDogEntity;
import com.github.axinger.db2.service.SysDogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Demo02DynamicDatasourceApplicationTest {

    @Autowired
    private SysPersonService sysPersonService;

    @Autowired
    private SysDogService sysDogService;

    @Test
    void test12() {
        List<SysPersonEntity> list = sysPersonService.list();
        System.out.println("list = " + list);
    }

    @Test
    void test2() {
        List<SysDogEntity> list = sysDogService.list();
        System.out.println("list = " + list);
    }


}
