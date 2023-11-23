package com.github.axinger;

import com.github.axinger.domain.*;
import com.github.axinger.dto.DepartmentDTO;
import com.github.axinger.dto.EmployeeDTO;
import com.github.axinger.dto.EmployeeDTO2;
import com.github.axinger.mapper.PersonMapper;
import com.github.axinger.service.*;
import com.mybatisflex.core.logicdelete.LogicDeleteManager;
import com.mybatisflex.core.query.FunctionQueryColumn;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.core.update.UpdateWrapper;
import com.mybatisflex.core.util.UpdateEntity;
import org.apache.ibatis.cursor.Cursor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.github.axinger.domain.table.AccountTableDef.ACCOUNT;
import static com.github.axinger.domain.table.ArticleTableDef.ARTICLE;
import static com.github.axinger.domain.table.DepartmentTableDef.DEPARTMENT;
import static com.github.axinger.domain.table.EmployeeTableDef.EMPLOYEE;
import static com.github.axinger.domain.table.PersonTableDef.PERSON;
import static com.mybatisflex.core.constant.FuncName.*;
import static com.mybatisflex.core.query.QueryMethods.*;

@SpringBootTest
class MybatisFlexApplicationTest {

    @Autowired
    private ITbAccountService iTbAccountService;

    @Autowired
    private PersonService personService;

    //    insert(entity)：插入实体类数据，不忽略 null 值。
//    insertSelective(entity)：插入实体类数据，但是忽略 null 的数据，只对有值的内容进行插入。这样的好处是数据库已经配置了一些默认值，这些默认值才会生效。
    @Test
    public void testInsertWithRaw() {
        Account account = new Account();
        account.setUserName("michael");


//        account.setUserName();
        // 新增数据字段内容是数据库的某个 函数 或者 SQL片段 生成的内容，而非我们手动设置的内容。
        Account newAccount = UpdateWrapper.of(account)
//       .setRaw("birthday", "now()")
//       .setRaw(ACCOUNT.BIRTHDAY, "now()")
                .setRaw(Account::getBirthday, "now()")
                .toEntity();

        System.out.println("newAccount = " + newAccount);

        iTbAccountService.save(newAccount); // 不可以
//        iTbAccountService.getMapper().insert(newAccount);
    }


    @Test
    void test_删除() {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .from(ACCOUNT);
        queryWrapper.where(ACCOUNT.ID.eq(1));

//通过 queryWrapper 删除
        iTbAccountService.remove(queryWrapper);

        iTbAccountService.getMapper().deleteByCondition(ACCOUNT.ID.le(10));
    }

    @Test
    void test_更新1() {

        Account account = UpdateEntity.of(Account.class, 1);
        account.setUserName(null);
        account.setAge(10);
        iTbAccountService.getMapper().update(account);

        iTbAccountService.updateById(account);
    }

    @Test
    void test_更新2() {

        Account account = new Account();
        account.setId(1);
        account.setUserName("jim");
        account.setAge(11);

        iTbAccountService.updateById(account);
    }

    @Test
    void test_更新3() {

        // QueryWrapper 条件修改
        Account account = new Account();
        account.setUserName(null);
        account.setAge(12);

        QueryWrapper queryWrapper = QueryWrapper.create()
                .from(ACCOUNT)
                .where(ACCOUNT.ID.eq(1));
        iTbAccountService.update(account, queryWrapper);
    }

    @Test
    void test_更新4() {

        Account account = UpdateEntity.of(Account.class, 1);

//        account.setUserName(null);

// 通过 UpdateWrapper 操作 account 数据
        UpdateWrapper<Account> wrapper = UpdateWrapper.of(account);
//        wrapper.setRaw("age", "age + 1");
        //更高级的用法
        wrapper.set(ACCOUNT.AGE, ACCOUNT.AGE.add(1));
        wrapper.set(Account::getUserName, "张三");


        //会直接参与 SQL 拼接，可能会造成 SQL 错误
        wrapper.setRaw(Account::getUserName, "张三");


//        wrapper.set(ACCOUNT.USER_NAME,"TOM");
//        iTbAccountService.getMapper().update(account);
        iTbAccountService.updateById(wrapper.toEntity());


    }

    @Test
    void test_更新5() {
        UpdateChain.of(Account.class)
                .from(ACCOUNT)
                .set(Account::getUserName, "张三")
                .setRaw(Account::getAge, "age + 1")
                .where(Account::getId).eq(1)
                .update();

    }


    @Test
    void test_更新_填充() {

        Person person = personService.getById(2);
//
        UpdateWrapper<Person> wrapper = UpdateWrapper.of(person);
        wrapper.set(PERSON.AGE, PERSON.AGE.add(1));
//        personService.updateById(wrapper.toEntity());


        UpdateChain.of(personService.getMapper())
                .from(PERSON)
                .set(PERSON.AGE, PERSON.AGE.add(1))
                .where(PERSON.ID.eq(2)
                        .and(PERSON.VERSION.eq(person.getVersion()))
                )
                .update();

    }

    @Test
    public void test_查询1() {

        QueryWrapper wrapper = QueryWrapper.create()
                .from(ACCOUNT)
                .select()
                .where(ACCOUNT.ID.eq(1));
        Account account = iTbAccountService.getOne(wrapper);
        System.out.println("account = " + account);

    }

    @Test
    public void test_查询2() {

        //进行事务操作，
        Db.tx(() -> {

            QueryWrapper wrapper = QueryWrapper.create()
                    .from(ACCOUNT)
                    .select()
                    .where(ACCOUNT.ID.ge(1));

            //游标查询
//            但由于游标查询是在 for 循环的时候，才去数据库拿数据。
//            因此必须保证 selectCursorByQuery 方法及其处理必须是在事务中进行，才能保证其链接并未与数据库断开。
//            以下场景经常需要用到游标查询功能：
//
//            1、数据查询并写入到缓存
//            2、Excel 导出等
            Cursor<Account> accounts = iTbAccountService.getMapper().selectCursorByQuery(wrapper);


            for (Account account : accounts) {
                System.out.println(account);
            }
            return true;
        });
    }

    @Test
    public void test_查询_自动映射() {
        Account list = QueryChain.of(iTbAccountService.getMapper())
                .from(ACCOUNT)
                .select(
                        groupConcat(ACCOUNT.ID),
                        max(ACCOUNT.AGE).as(Account::getMaxAge),
                        min(ACCOUNT.AGE).as(Account::getMimAge),
                        avg(ACCOUNT.AGE).as(Account::getAvgAge)
                ).where(ACCOUNT.ID.ge(1))
                .one();
        System.out.println("list = " + list);
    }

    @Test
    public void test_查询_分组() {
        List<Account> list = QueryChain.of(iTbAccountService.getMapper())
                .from(ACCOUNT)
                .select(
                        groupConcat(ACCOUNT.ID).as(Account::getGroupAfterId),
                        new FunctionQueryColumn(GROUP_CONCAT, ACCOUNT.USER_NAME).as(Account::getUserName),
                        ACCOUNT.AGE
                )
                .where(ACCOUNT.ID.ge(1))
                .groupBy(ACCOUNT.AGE)
                .list();
        System.out.println("list = " + list);
    }


    @Test
    public void test_查询_逻辑删除() {

        List<Person> list = personService.list();
        System.out.println("list = " + list);

        List<Person> list1 = QueryChain.of(personService.getMapper())
                .from(PERSON)
                .select(PERSON.ALL_COLUMNS)
                .list();
        System.out.println("list1 = " + list1);


        // 跳过逻辑删除,查询
        LogicDeleteManager.execWithoutLogicDelete(() -> {
            List<Person> list2 = QueryChain.of(personService.getMapper())
                    .from(PERSON)
                    .select(PERSON.DEFAULT_COLUMNS)
                    .list();

            System.out.println("list2 = " + list2);
        });

    }

    @Autowired
    private ITbArticleService iTbArticleService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void test_多表查询() {

        QueryWrapper query = QueryWrapper.create()
                .select(ARTICLE.ALL_COLUMNS)
                .select(ACCOUNT.USER_NAME.as(Article::getAuthorName)
                        , ACCOUNT.AGE.as(Article::getAuthorAge)
                        , ACCOUNT.BIRTHDAY
                )
                .from(ARTICLE)
                .leftJoin(ACCOUNT).on(ARTICLE.ACCOUNT_ID.eq(ACCOUNT.ID))
                .where(ACCOUNT.ID.ge(1));

        List<Article> results = iTbArticleService.getMapper().selectListByQueryAs(query, Article.class);
        System.out.println(results);

    }

    @Test
    public void test_多表查询_自定义对象字段映射() {
        List<EmployeeDTO> list = QueryChain.of(employeeService.getMapper())
                .select(
                        EMPLOYEE.ALL_COLUMNS, //员工所有字段
                        DEPARTMENT.NAME.as(EmployeeDTO::getDeptName) //部门字段,别名
                ).from(EMPLOYEE)
                .leftJoin(DEPARTMENT).on(DEPARTMENT.ID.eq(EMPLOYEE.DEPT_ID))
                .where(EMPLOYEE.ID.ge(1))
                .listAs(EmployeeDTO.class);
        System.out.println("list = " + list);

    }

    @Test
    public void test_多表查询_自定义对象字段映射2() {

        List<EmployeeDTO2> list = QueryChain.of(employeeService.getMapper())
                .select(
                        EMPLOYEE.DEFAULT_COLUMNS, //员工所有字段
                        DEPARTMENT.DEFAULT_COLUMNS //部门字段,别名
//                        DEPARTMENT.ID.as(DepartmentDTO::getId),
//                        DEPARTMENT.NAME.as(DepartmentDTO::getName)
                ).from(EMPLOYEE)
                .leftJoin(DEPARTMENT).on(DEPARTMENT.ID.eq(EMPLOYEE.DEPT_ID))
                .where(EMPLOYEE.ID.ge(1))
                .listAs(EmployeeDTO2.class);
        System.out.println("list = " + list);

    }

    @Test
    public void test_多表查询_一对多() {

        List<Department> departments = departmentService.getMapper().selectAllWithRelations();
        System.out.println(departments);
    }

    @Test
    public void test_多表查询_关系为多对一() {

        List<Employee> employees = employeeService.getMapper().selectAllWithRelations();

//        QueryWrapper queryWrapper = QueryWrapper.create()
//                .from(EMPLOYEE);
//        queryWrapper.select(EMPLOYEE.ID,EMPLOYEE.NAME,EMPLOYEE.DEPT_ID,EMPLOYEE.DEPARTMENT);
//        List<Employee> employees = employeeService.getMapper().selectListByQuery(queryWrapper)
        ;
        System.out.println(employees);
    }
}
