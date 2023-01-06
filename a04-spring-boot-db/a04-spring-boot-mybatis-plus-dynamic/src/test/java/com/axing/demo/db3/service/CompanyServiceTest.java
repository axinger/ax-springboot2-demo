package com.axing.demo.db3.service;

import com.axing.demo.db3.domain.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

    @Test
    void test1() {
        List<Company> list = companyService.list();
        System.out.println("list = " + list);
    }

    @Test
    void test2() {
        List<Company> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Company company = new Company();
            company.setName("jim_"+i);
            company.setAge(10+i);
            list.add(company);
        }
        companyService.saveBatch(list);

    }

}
