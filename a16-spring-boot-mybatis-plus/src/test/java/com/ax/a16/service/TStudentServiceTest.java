package com.ax.a16.service;

import com.ax.a16.domain.TStudent;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
@Slf4j
class TStudentServiceTest {

    @Autowired
    TStudentService service;

    @Test
    public void test() {
        System.out.println("service.getById(1) = " + service.getById(1));
    }

    @Test
    public void test1() {
        // 条件查询
        QueryWrapper<TStudent> wrapper = new QueryWrapper<>();
        wrapper.ge("age", 21);

        // 手动添加 limit 有sql注入的风险,请谨慎使用
        wrapper.last("limit 1");

        // 注释
//        wrapper.comment("我是注释");

        System.out.println("getOne = " + service.getOne(wrapper));
//        System.out.println("getOne = " + service.getBaseMapper().selectOne(wrapper));
    }

    @Test
    public void test2() {
        log.info("===系统调用了TestController3===");
        System.out.println("service.getById(1) = " + service.listByIds(Arrays.asList(1, 2)));
    }

    @Test
    public void test3() {
        System.out.println("selectList = " + service.getBaseMapper().selectList(null));
    }

    @Test
    public void test4() {

        // 修改
        service.updateById(TStudent.builder().id(1L).age(21).build());
    }

    @Test
    public void test5() {

        // 自动填充, 添加数据
//        service.save(TStudent.builder().name("tom").age(21).crateTime(new Date()).build());
        service.save(TStudent.builder().name("tom").age(21).build());
    }

    @Test
    public void test6() {

        // 乐观锁

        final TStudent student = service.getById(1);
        student.setAge(23);
        System.out.println("student = " + student);
        service.updateById(student);
    }

    @Test
    public void test7() {

        // 分页
        Page<TStudent> page = new Page(1, 3);
        page.setSize(3);
        TStudent student = new TStudent();
        student.setName("jim");

        //设置条件
        QueryWrapper<TStudent> wrapper = new QueryWrapper<>();
        //eq是等于，ge是大于等于，gt是大于，le是小于等于，lt是小于，like是模糊查询


//            wrapper.like("name","jim");

        wrapper.ge("age", 23);

        final Page<TStudent> page1 = service.page(page, wrapper);
        System.out.println("service.page(page) = " + page1);
        System.out.println("ge1.getRecords() = " + page1.getRecords());

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(page1);
            System.out.println("service.page(page) = " + json + page1.hasNext());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test8() {
        // 逻辑删除,添加字段 对应  物理删除,真正删除
        System.out.println("逻辑删除 = " + service.removeById(2));
        System.out.println("逻辑删除后查询 = " + service.getById(2));
    }
}
