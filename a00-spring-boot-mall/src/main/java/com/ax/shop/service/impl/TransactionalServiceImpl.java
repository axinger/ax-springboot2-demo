package com.ax.shop.service.impl;

import com.ax.shop.entity.TStudent;
import com.ax.shop.entity.Userinfo;
import com.ax.shop.mapper.TStudentMapper;
import com.ax.shop.mapper.UserinfoMapper;
import com.ax.shop.service.TransactionalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.IOException;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName TransactionalServiceImpl.java
 * @Description TODO
 * @createTime 2021年12月28日 10:16:00
 */

@Service
@Slf4j
public class TransactionalServiceImpl implements TransactionalService {

    @Autowired
    private TStudentMapper studentMapper;

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;


    public void addStudent() {
        final TStudent student = TStudent.builder()
                .name("name_tra")
                .age(12)
                .address("大街2")
                .build();
        studentMapper.insert(student);
    }

    public void addUserInfo(Boolean isError) {
        final Userinfo student = Userinfo.builder()
                .userName("name_tra")
                .password("abc123")
                .build();
        userinfoMapper.insert(student);
        if (isError) {
            /// https://www.jianshu.com/p/c5988db897fc
            /// 不加rollbackFor属性，抛出RuntimeException，正常回滚
            throw new RuntimeException();


            /// 不加rollbackFor属性，抛出IOException，不回滚
//            throw new IOException("");
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object insetSuccess(Boolean isError) {
        addStudent();
        addUserInfo(isError);
        System.out.println("事务成功: 有rollbackFor 参数");
        return "成功";
    }


   /**
    * 如果只这样写 @Transactional ,
    * Spring框架的事务基础架构代码将默认地只在抛出运行时和unchecked exceptions时才标识事务回滚。
    * */
    @Override
    @Transactional
    public Object insetSuccess2(Boolean isError) {
        addStudent();
        addUserInfo(isError);
        System.out.println("事务成功: 没有rollbackFor 参数");
        return null;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object insetSuccessWithTemplate(Boolean isError) {

        final String s = transactionTemplate.execute((status) -> {

            addStudent();
            addUserInfo(isError);
            System.out.println("事务成功: 有rollbackFor 参数");

            return "事务起作用了";
        });

        System.out.println("s = " + s);

        return "成功";
    }
}
