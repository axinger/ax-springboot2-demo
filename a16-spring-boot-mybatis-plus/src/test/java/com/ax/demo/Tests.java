package com.ax.demo;

import com.ax.demo.domain.Emps;
import com.ax.demo.domain.User;
import com.ax.demo.mapper.UserMapper;
import com.ax.demo.service.EmpsService;
import com.ax.demo.service.UserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName Tests.java
 * @Description TODO
 * @createTime 2022年03月13日 12:34:00
 */
@SpringBootTest
@Slf4j
public class Tests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;


    @Test
    public void testSelect() {
        List<User> userList = userService.list();
        System.out.println("userList = " + userList);

        userList.forEach(val->{
            System.out.println("val.getGender() = " + val.getGender());

            switch (val.getGender()){
                case male:
                    System.out.println("男");
                    break;
                case female:
                    System.out.println("女");
                    break;
                case unknown:
                    System.out.println("未知");
                    break;
            }
        });
    }


    /**
     * 根据id修改
     * UPDATE user SET user_name=?, user_age=? WHERE (id = ?)
     */
    @Test
    public void testudpateById() {
        User user = new User();
        user.setAge(25);
        user.setName("test update");
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", "3");
        int num = userMapper.update(user, updateWrapper);
        System.out.println("修改的记录数为：" + num);
    }

    /**
     * 查询指定记录
     * SELECT id,user_name,user_age FROM user WHERE (user_name = ?)
     */
    @Test
    public void testSelectWrapper() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_name", "IT可乐");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(x -> System.out.println(x.getId() + "-" + x.getName() + "-" + x.getAge()));
    }

    /**
     * 新增一条记录
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setId(4L);
        user.setName("test insert");
        user.setAge(1);
        int insert = userMapper.insert(user);
        System.out.println("影响记录数：" + insert);
    }

    @Test
    public void testGet() {
        log.info("控制台颜色===========");
        System.out.println("userMapper.selectById(4L) = " + userMapper.selectById(4L));
    }

    /**
     * allEq 全部等于
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (user_name = ? AND id = ?)
     */
    @Test
    public void testAllEq() {
        QueryWrapper queryWrapper = new QueryWrapper();
        Map map = new HashMap<>();
        map.put("id", "3");
        map.put("user_name", "IT可乐");
        queryWrapper.allEq(map);
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * eq 等于
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (id = ?)
     */
    @Test
    public void testEq() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", "3");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /**
     * ne 不等于
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (id <> ?)
     */
    @Test
    public void testNe() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.ne("id", "3");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /**
     * gt 大于
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (user_age > ?)
     */
    @Test
    public void testGt() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.gt("user_age", "18");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /**
     * ge 大于等于
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (user_age >= ?)
     */
    @Test
    public void testGe() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.ge("user_age", "18");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /**
     * lt 小于
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (user_age < ?)
     */
    @Test
    public void testLt() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.lt("user_age", "18");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /**
     * le 小于等于
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (user_age <= ?)
     */
    @Test
    public void testLe() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.le("user_age", "18");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /**
     * between 值1和值2之间,两边临界值都包含
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (user_age BETWEEN ? AND ?)
     */
    @Test
    public void testBetween() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.between("user_age", "18", "25");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * notBetween 不在值1和值2之间，两边临界值都包含
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (user_age NOT BETWEEN ? AND ?)
     */
    @Test
    public void testNoBetween() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.notBetween("user_age", "18", "25");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /**
     * like 模糊查询，会在参数左右两边加上 %
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (user_name LIKE ?)
     */
    @Test
    public void testLike() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("user_name", "可乐");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * notLike NOT LIKE ‘%parameter%’
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (user_name NOT LIKE ?)
     */
    @Test
    public void testNotLike() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.notLike("user_name", "可乐");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /**
     * likeLeft LIKE ‘%parameter’
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (user_name LIKE '%parameter')
     */
    @Test
    public void testLikeLeft() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.likeLeft("user_name", "可乐");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * likeRight LIKE ‘parameter%’
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (user_name LIKE 'parameter%')
     */
    @Test
    public void testLikeRight() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.likeRight("user_name", "可乐");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /**
     * isNull 判断字段为null
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (user_name IS NULL)
     */
    @Test
    public void testIsNull() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.isNull("user_name");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * isNotNull 判断字段不为null
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (user_name IS NOT NULL)
     */
    @Test
    public void testIsNotNull() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.isNotNull("user_name");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * in 范围定值查询
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (user_age IN (?,?,?))
     */
    @Test
    public void testIn() {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<Integer> queryList = new ArrayList<>();
        queryList.add(18);
        queryList.add(1);
        queryList.add(25);
        queryWrapper.in("user_age", queryList);
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * notIn
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (user_age IN (?,?,?))
     */
    @Test
    public void testNotIn() {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<Integer> queryList = new ArrayList<>();
        queryList.add(18);
        queryList.add(1);
        queryList.add(25);
        queryWrapper.notIn("user_age", queryList);
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * inSql
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (id IN (select id from user))
     */
    @Test
    public void testInSql() {
        QueryWrapper queryWrapper = new QueryWrapper();
        //查询所有数据
        queryWrapper.inSql("id", "select id from user");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * notInSql
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (id NOT IN (select id from user where id > 2))
     */
    @Test
    public void testNotInSql() {
        QueryWrapper queryWrapper = new QueryWrapper();
        //查询所有数据
        queryWrapper.notInSql("id", "select id from user where id > 2");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * groupBy 分组
     * 下面SQL有个问题，在MySQL8.0版本中，是可以执行下面SQL语句的，select user_name并没有出现在group by 语句中
     * 实例SQL：SELECT id,user_name,user_age FROM user GROUP BY id,user_age
     */
    @Test
    public void testGroupBy() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.groupBy("id", "user_age");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println("groupBy = " + list);
    }


    /**
     * orderByAsc 升序
     * 实例SQL：SELECT id,user_name,user_age FROM user ORDER BY id ASC,user_age ASC
     */
    @Test
    public void testOrderByAsc() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByAsc("id", "user_age");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * orderByDesc 降序
     * 实例SQL：SELECT id,user_name,user_age FROM user ORDER BY id DESC,user_age DESC
     */
    @Test
    public void testOrderByDesc() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("id", "user_age");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * orderBy 指定顺序排序
     * 实例SQL：SELECT id,user_name,user_age FROM user ORDER BY id ASC,user_age ASC
     */
    @Test
    public void testOrderBy() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderBy(true, true, "id", "user_age");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * having
     * 实例SQL：SELECT id,user_name,user_age FROM user GROUP BY id,user_age HAVING sum(user_age)>?
     */
    @Test
    public void testHaving() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.groupBy("id", "user_age");
        queryWrapper.having("sum(user_age)>{0}", "25");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * having
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (id = ? OR user_age = ?)
     */
    @Test
    public void testOr() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", 1);
        queryWrapper.or();
        queryWrapper.eq("user_age", 25);
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * and
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE ((id = ? AND user_age <> ?))
     */
    @Test
    public void testAnd() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(i -> i.eq("id", 1).ne("user_age", 18));
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * nested
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE ((id = ? AND user_age <> ?))
     */
    @Test
    public void testNested() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.nested(i -> i.eq("id", 1).ne("user_age", 18));
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * apply
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (user_age>?)
     */
    @Test
    public void testApplyd() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("user_age>{0}", "25 or 1=1");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * last
     * 实例SQL：SELECT id,user_name,user_age FROM user limit 1
     */
    @Test
    public void testLast() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.last("limit 1 ");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * exists
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (EXISTS (select id from user where user_age = 1))
     */
    @Test
    public void testExists() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.exists("select id from user where user_age = 1");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }


    /**
     * notExists
     * 实例SQL：SELECT id,user_name,user_age FROM user WHERE (EXISTS (select id from user where user_age = 1))
     */
    @Test
    public void testNotExists() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.notExists("select id from user where user_age = 1");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /**
     * LambdaQueryWrapper和LambdaUpdateWrapper（推荐）
     *
     * LambdaQueryWrapper 和 LambdaUpdateWrapper 这是相对于 QueryWrapper 及 UpdateWrapper 的 Lmbda 语法实现方式。
     *
     * //两种方式
     * LambdaQueryWrapper queryLambda = new QueryWrapper().lambda();
     * LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<>();
     *
     * //两种方式
     * LambdaUpdateWrapper updateLambda = new UpdateWrapper().lambda();
     * LambdaUpdateWrapper lambdaUpdateWrapper = new LambdaUpdateWrapper();
     * */

    /**
     * LambdaQueryWrapper
     * SQL实例：SELECT id,user_name,user_age FROM user WHERE (id = ? AND user_age <> ?)
     */
    @Test
    public void testLambdaQueryWrapper() {
//        LambdaQueryWrapper<User> queryLambda = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> queryLambda = new QueryWrapper().lambda();

        // ne 不等于 &lt;&gt;
        queryLambda.eq(User::getName, "jim").ne(User::getAge, 21);
        List<User> users = userMapper.selectList(queryLambda);

        System.out.println("users = " + users);


        // in list
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.in(User::getId,"1","2");
        List<User> users2 = userMapper.selectList(lambdaQuery);
        System.out.println("in list users2 = " + users2);

    }

    /**
     * LambdaQueryWrapper
     * SQL实例：UPDATE user SET user_name=? WHERE (user_name = ?)
     */
    @Test
    public void testLambdaUpdateWrapper() {
        User user = new User();
        user.setName("LambdaUpdateWrapper");
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userLambdaUpdateWrapper.eq(User::getName, "jim");
        userMapper.update(user, userLambdaUpdateWrapper);

    }

    @Autowired
    EmpsService empsService;

    /**
     * 多表查询 1对1
     */
    @Test
    void test_EmpsService(){

        final Emps emps = empsService.getEmployeeandDepartment(1);
        System.out.println("emps = " + emps);

    }

    @Test
    void test_EmpsService_page(){

        Page<Emps> page = new Page<Emps>(1, 5);

        LambdaQueryWrapper<Emps> lambdaQuery = Wrappers.lambdaQuery();
//        wrapper.in(Emps::getId,1,2);


        final IPage<Emps> empsIPage = empsService.getPage(page, lambdaQuery);
        System.out.println("多表分页 = " + empsIPage.getRecords());


        // 字段重名
        QueryWrapper<Emps> wrapper = Wrappers.query();
        wrapper.in("emps.id",1);

        final IPage<Emps> empsIPage2 = empsService.getPage(page, wrapper);
        System.out.println("多表分页 id 重名 = " + empsIPage2.getRecords());


    }
}
