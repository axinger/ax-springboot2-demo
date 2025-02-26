package com.github.axinger.service;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.axinger.config.MyTableNameHandler;
import com.github.axinger.config.MyTenantLineHandler;
import com.github.axinger.domain.SysPersonEntity;
import com.github.axinger.mapper.SysPersonMapper;
import com.github.axinger.vo.SysPersonVO;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SysPersonServiceTest {

    @Autowired
    private SysPersonService sysPersonService;

    @Autowired
    private SysPersonMapper sysPersonMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    void test_新增list() {
        String jsonList = """
                [
                    {
                        "name": "jim",
                        "age": 10,
                        "id":1
                    },
                    {
                        "name": "tom",
                        "age": 10,
                        "id":2
                    },
                    {
                        "name": "lili",
                        "age": 11,
                        "id":3
                    }
                ]
                """;
        List<SysPersonEntity> entityList = JSON.parseArray(jsonList, SysPersonEntity.class);
//        personService.saveBatch(entityList);

        int i = sysPersonMapper.insertBatchSomeColumn(entityList);
        System.out.println("i = " + i);
    }

    @Test
    void test_新增一个() {
        String jsonList = """
                    {
                        "name": "jim",
                        "age": 10,
                        "id":1
                    }
                """;


        SysPersonEntity person = JSONObject.parseObject(jsonList, SysPersonEntity.class);
        sysPersonService.save(person);
    }

    @Test
    void test_saveOrUpdateBatch() {
        SysPersonEntity book = sysPersonService.getById(1);
        book.setName(book.getName() + "_v1");
        List<SysPersonEntity> list = List.of(book);
        sysPersonService.saveOrUpdateBatch(list);
    }

    @Test
    void test_saveOrUpdateBatch2() {
        SysPersonEntity book = sysPersonService.getById(1);
        book.setName(book.getName() + "_v1");

        SysPersonEntity book1 = new SysPersonEntity();
        book1.setName("一千零一夜");

        List<SysPersonEntity> list = List.of(book, book1);
        sysPersonService.saveOrUpdateBatch(list);
    }

    ///  ResultHandler 比 Cursor 简单
    @Test
    void test_stream() throws InterruptedException {

        sysPersonMapper.selectStream(val -> {
            SysPersonEntity entity = val.getResultObject();
            System.out.println("entity = " + entity);
        });

        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    void test_findAll_stream2() {
        LambdaQueryWrapper<SysPersonEntity> wrapper = Wrappers.<SysPersonEntity>lambdaQuery()
                .ge(SysPersonEntity::getAge, 10);

        sysPersonMapper.selectStreamWrapper(wrapper, val -> {
            SysPersonEntity resultObject = val.getResultObject();
            int resultCount = val.getResultCount();
            System.out.println("resultCount = " + resultCount);
            System.out.println("resultObject = " + resultObject);
        });
    }

    @Test
    void test_cursorSelect() throws IOException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            SysPersonMapper mapper = session.getMapper(SysPersonMapper.class);
            try (Cursor<SysPersonEntity> entities = mapper.cursorSelect()) {
                for (SysPersonEntity entity : entities) {
                    System.out.println("entity = " + entity);
                }
            }
        }
    }


    @Test
    void test_findAll() {

        List<SysPersonEntity> list = sysPersonMapper.myFindAll();
        System.out.println("list = " + list);
    }

    /// 自定义返回对象
    @Test
    void test_findAll_vo() {

        List<SysPersonVO> list = sysPersonMapper.selectListWihVO();
        System.out.println("list = " + list);
    }

    @Test
    void test_findAll_vo2() {
        SysPersonVO build = SysPersonVO.builder().build();
        System.out.println("build = " + build);
    }


    @Test
    void test() {
        List<SysPersonEntity> list = sysPersonService.list();
        System.out.println("list = " + JSON.toJSON(list));
    }


    @Test
    void test_lambdaQuery() {
        // lambdaQuery ( 对象 ) 就相当于 ==
        SysPersonEntity book = new SysPersonEntity();
        book.setName("jim");
        List<SysPersonEntity> list1 = sysPersonService.lambdaQuery(book)
                .eq(SysPersonEntity::getId, 1)
                .list();
        System.out.println("list1 = " + list1);
    }

    @Test
    void test_lambdaQuery2() {

        LambdaQueryChainWrapper<SysPersonEntity> wrapper = sysPersonService.lambdaQuery();

        wrapper.or(w -> {
            w.eq(SysPersonEntity::getName, "jim")
                    .eq(SysPersonEntity::getAge, 10);
        });

        wrapper.or(w -> {
            w.eq(SysPersonEntity::getName, "tom")
                    .eq(SysPersonEntity::getAge, 20);
        });

        List<SysPersonEntity> list = wrapper.list();
        System.out.println("list = " + list);
    }

    @Test
    void test_LambdaUtils() {
        LambdaMeta extract = LambdaUtils.extract(SysPersonEntity::getName);
        System.out.println("extract.getImplMethodName() = " + extract.getImplMethodName()); // getBookName
        System.out.println("extract.getInstantiatedClass() = " + extract.getInstantiatedClass()); // class com.github.axinger.domain.BookEntity
    }

    @Test
    void test_save() {
        sysPersonService.removeById(1L);
        SysPersonEntity book1 = new SysPersonEntity();
        book1.setId(1L);
        book1.setName("tom");
        sysPersonService.save(book1);
    }

    @Test
    void test_分页() {
        Page<SysPersonEntity> page = sysPersonService.lambdaQuery()
                .page(Page.of(1, 100));
        System.out.println("page = " + page.getRecords());
    }

    @Test
    void test_更新全表() {
        sysPersonService.lambdaUpdate()
                .setSql("age = age+1")
                .update(new SysPersonEntity());
    }


    @Test
    void test_updateById() {
        SysPersonEntity book = sysPersonService.getById(1);
        // book.setVersion(book.getVersion()+1);
        // book.setBookName(book.getBookName()+"_v1");
        // book.setBookName("_v1");
        book.setAge(10); // 自动+1,再set值,就无效,@Version原理如此
        sysPersonService.updateById(book);
    }

    @Test
    void test_updateById_bookPrice() {
        sysPersonService.lambdaUpdate()
                .eq(SysPersonEntity::getId, 1)
                .setSql("age = age+1")
                .update(new SysPersonEntity());
    }

    @Test
    void test_updateById_bookPrice2() {


//        LambdaUpdateWrapper<BookEntity> updateWrapper = Wrappers.lambdaUpdate();

        LambdaUpdateWrapper<SysPersonEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SysPersonEntity::getId, 1);


        // 对 age 字段进行自增操作

        // 对 salary 字段进行数学计算
//        updateWrapper.set(BookEntity::getBookPrice, (item)->{
//        return  1D;
//        });


    }

    @Test
    void test_updateById_bookPrice_2() {
        // 有bug
        SysPersonEntity entity = new SysPersonEntity();
        entity.setAge(10);
        entity.setId(1L);
        AbstractWrapper<SysPersonEntity, SFunction<SysPersonEntity, ?>, LambdaUpdateWrapper<SysPersonEntity>> wrapper = sysPersonService.lambdaUpdate()
                .eq(SysPersonEntity::getId, 1)
                .setSql("book_price = book_price+10")
                .getWrapper();

//        personService.saveOrUpdate(entity, wrapper);
        sysPersonService.update(entity, wrapper);
    }

    @Test
    void test_lambdaUpdate() {
        //  1.不会会自动填充
        SysPersonEntity book = sysPersonService.getById(1);
        sysPersonService.lambdaUpdate()
                .ge(SysPersonEntity::getId, 1)
                .set(SysPersonEntity::getName, book.getName() + "_v1")
                .update();
    }

    @Test
    void test_lambdaUpdate1() {
        // bookService.lambdaUpdate()
        //         .eq(BookEntity::getBookAuthor, "tom")
        //         .set(BookEntity::getBookName, "一千零一夜")
        //         .update(new BookEntity());

        SysPersonEntity book = new SysPersonEntity();
        book.setName("tom");
        book.setAge(20);

        // bookService.saveOrUpdate(book, Wrappers.<BookEntity>lambdaQuery()
        //         .eq(BookEntity::getBookAuthor, "tom"));

        sysPersonService.update(book, sysPersonService.lambdaQuery()
                .eq(SysPersonEntity::getName, "tom")
                .getWrapper());

    }

    @Test
    void test_lambdaUpdate2() {
        //  1.update(new 对象)才会自动填充
        //  2. 乐观锁失效,不支持,需要手动添加锁
        SysPersonEntity book = sysPersonService.getById(1);
        sysPersonService.lambdaUpdate()
                .eq(SysPersonEntity::getId, 1)
                .set(SysPersonEntity::getName, "jim")
                .update(new SysPersonEntity());
    }

    @Test
    void test_lambdaUpdate3() {
        //  乐观锁失效,不支持,需要手动添加锁
        SysPersonEntity book = sysPersonService.getById(1);
        sysPersonService.lambdaUpdate()
                .eq(SysPersonEntity::getId, 1)
                .eq(SysPersonEntity::getVersion, book.getVersion())
                .set(SysPersonEntity::getName, "jim")
                .set(SysPersonEntity::getVersion, book.getVersion() + 1)
                .update(new SysPersonEntity());
    }


    @Test
    void test2() {
        LocalDateTime dateTime = LocalDateTime.now().plusDays(-1);

        LocalDateTime startTime = LocalDateTimeUtil.beginOfDay(dateTime);
        LocalDateTime endTime = LocalDateTimeUtil.endOfDay(dateTime);
        List<SysPersonEntity> list = sysPersonService.lambdaQuery()
                .ge(SysPersonEntity::getBirthday, startTime)
                .list();
        System.out.println("list = " + list);
    }

    @Test
    void test_remove() {
        boolean remove = sysPersonService.lambdaUpdate()
                .remove();
        System.out.println("remove = " + remove);
    }


    @Test
    void test3() {

        SysPersonMapper baseMapper = (SysPersonMapper) sysPersonService.getBaseMapper();
        IPage<SysPersonEntity> page = baseMapper.customSqlSegment(Wrappers.<SysPersonEntity>lambdaQuery()
                        .select(SysPersonEntity::getId)
                        .gt(SysPersonEntity::getAge, 10),
                Page.of(1, 3));
        System.out.println("page.getSize() = " + page.getSize());
        System.out.println("page.getPages() = " + page.getPages());
        System.out.println("page.getCurrent() = " + page.getCurrent());
        System.out.println("page.getTotal() = " + page.getTotal());
        System.out.println(page.getRecords());
    }

    @Test
    void test_tableListHasDelete() {

        SysPersonMapper baseMapper = (SysPersonMapper) sysPersonService.getBaseMapper();
        IPage<SysPersonEntity> page = baseMapper.sqlSegment(Wrappers.<SysPersonEntity>lambdaQuery()
                        // .select(BookEntity::getId)
                        .gt(SysPersonEntity::getAge, 10),
                Page.of(1, 3));
        System.out.println("page.getSize() = " + page.getSize());
        System.out.println("page.getPages() = " + page.getPages());
        System.out.println("page.getCurrent() = " + page.getCurrent());
        System.out.println("page.getTotal() = " + page.getTotal());
        System.out.println(page.getRecords());
    }


    // 动态表名
    @Test
    @SuppressWarnings(value = "all")
    void test_动态表() {
        MyTableNameHandler.setData("_01");//此处调用方法
        List<SysPersonEntity> list = sysPersonService.list();
        System.out.println("list = " + list);
    }

    @Test
    @SuppressWarnings(value = "all")
    void test_多租户() {
        MyTenantLineHandler.setTenantId("100");
        List<SysPersonEntity> list = sysPersonService.list();
        System.out.println("list = " + list);
    }
}
