package com.axing.demo.service;

import cn.hutool.core.util.ObjUtil;
import com.alibaba.fastjson2.JSON;
import com.axing.demo.domain.Student;
import com.axing.demo.enums.Gender;
import com.axing.demo.mapper.StudentMapper;
import com.axing.demo.service.StudentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Slf4j
class StudentServiceTest {

    @Autowired
    StudentService service;

    @Test
    public void test_IService() {
        IService<Student> iService = new ServiceImpl<StudentMapper, Student>();
        final List<Student> list = iService.list();
        System.out.println("list = " + list);
    }

    @Test
    public void save() {

        String json = """
                [
                    {
                        "id": 1,
                        "age": 20,
                        "name": "jim",
                        "gender": 2
                    },
                    {
                        "id": 2,
                        "address": "幸运大街",
                        "age": 20,
                        "name": "tom",
                        "gender": 1
                    },
                    {
                        "id": 3,
                        "address": "幸福大街",
                        "age": 20,
                        "name": "lili",
                        "gender": 2
                    }
                ]
                """;

        List<Student> studentList = JSON.parseArray(json, Student.class);
        System.out.println("service.saveBatch(studentList) = " + service.saveBatch(studentList));
    }

    @Test
    public void find_1() {

        // 条件少, or 是可以的
        List<Student> list = service.lambdaQuery()
                .eq(Student::getAddress, "幸福大街")
                .or()
                .isNull(Student::getAddress)
                .list();
        System.out.println("list = " + list);
    }

    @Test
    public void find_2() {

        // 条件多, or
        /**
         SELECT id,name,age,gender,address,create_time,update_time,deleted,version FROM t_student WHERE deleted=0 AND (gender = 2 AND address = '幸福大街' OR address IS NULL)
         */
        List<Student> list = service.lambdaQuery()
                .eq(Student::getGender, Gender.female)
                .eq(Student::getAddress, "幸福大街")
                .or()
                .isNull(Student::getAddress)
                .list();
        System.out.println("list = " + list);
    }

    /**
     * 查找 null 或者 指定 值
     */
    @Test
    public void find_3() {

        /**
         SELECT id,name,gender,sex,address,create_time,update_time,deleted,version FROM t_student WHERE deleted=0 AND (gender = 2 AND (address = '幸福大街' OR address IS NULL))
         */
        List<Student> list = service.lambdaQuery()
                .eq(Student::getGender, Gender.female)
                .and((query) -> query
                        .eq(Student::getAddress, "幸福大街")
                        .or()
                        .isNull(Student::getAddress))
                .comment("多个查询")
                .list();
        System.out.println("list = " + list);
    }

    @Test
    public void find_4() {
        // SELECT id,name,age,gender,address,create_time,update_time,deleted,version FROM t_student WHERE deleted=0 AND (gender = 2)
        //String address = null;

        // SELECT id,name,age,gender,address,create_time,update_time,deleted,version FROM t_student WHERE deleted=0 AND (gender = 2 AND address = '幸福大街') /*eq 判断条件*/
        String address = "幸福大街";
        List<Student> list = service.lambdaQuery()
                .eq(Student::getGender, Gender.female)
                .eq(ObjUtil.isNotEmpty(address),Student::getAddress, address)
                .comment("eq 判断条件")
                .list();
        System.out.println("list = " + list);
    }




    @Test
    public void update() {
        Student student = new Student();
        student.setId(1L);
        student.setName("tom2");
        student.setAge(19);
        service.updateById(student);
    }

    @Test
    public void list() {
        List<Student> list = service.list();
        System.out.println("list = " + list);
    }

    @Test
    public void test() {
        System.out.println("service.getById(1) = " + service.getById(1));
    }

    @Test
    public void test1() {
        // 条件查询
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.ge("age", 21);

        // 手动添加 limit 有sql注入的风险,请谨慎使用
        wrapper.last("limit 1");

        // 注释
        wrapper.comment("我是注释");

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
        service.updateById(Student.builder().id(1L).age(21).build());
    }

    @Test
    public void test5() {

        // 自动填充, 添加数据
//        service.save(Student.builder().name("tom").age(21).crateTime(new Date()).build());
        service.save(Student.builder().name("tom").age(21).build());
    }

    @Test
    public void test6_version() {

        // 乐观锁,version需要与数据库一直
        final Student student = service.getById(2);
        System.out.println("student before = " + student);
        student.setAge(student.getAge() + 1);
        System.out.println("student after = " + student);
        boolean updateById = service.updateById(student);
        System.out.println("updateById = " + updateById);
    }

    @Test
    public void test6_version_2() {

        // 乐观锁,version 不一致,更新失败
        final Student student = service.getById(1);
        System.out.println("student before = " + student);
        student.setAge(student.getAge() + 1);
        student.setVersion(student.getVersion() - 1);
        System.out.println("student after = " + student);
        boolean updateById = service.updateById(student);
        System.out.println("updateById = " + updateById);
    }

    @Test
    public void test7() {

        // 分页
        Page<Student> page = new Page(1, 3);
        page.setSize(3);
        Student student = new Student();
        student.setName("jim");

        //设置条件
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        //eq是等于，ge是大于等于，gt是大于，le是小于等于，lt是小于，like是模糊查询


//            wrapper.like("name","jim");

        wrapper.ge("age", 23);

        final Page<Student> page1 = service.page(page, wrapper);
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
