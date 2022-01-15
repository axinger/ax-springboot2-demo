package com.ax.mall.transactional;

import com.ax.mall.entity.Userinfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserServiceTest {

    @Autowired
    TransactionalUserService userService;

//    https://www.jianshu.com/p/c3e961205630

    /**
     * 保存了user1.
     * <p>
     * 结论：并行事务不存在事务影响
     */
    @Test
    public void test1() {
        userService.addUser1(Userinfo.builder().userName("jim_31").build());
        userService.addUser2(Userinfo.builder().userName("tom_31").build());
    }

    /**
     * 嵌套相同事务
     * <p>
     * 由于两个都是在一个事务当中，所以只要有一个方法事务有问题，那么都不会插入成功。
     */
    @Test
    public void test3() {
        userService.addUser3(Userinfo.builder().userName("jim_33").build());
    }

}