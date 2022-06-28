package com.ax.master.transactional;


import com.ax.master.entity.Userinfo;
import com.ax.master.service.IUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xing
 */
@Service
public class TransactionalUserService {
    @Autowired
    IUserinfoService userinfoService;


    @Transactional(rollbackFor = Exception.class)
    public void addUser1(Userinfo user) {
        final int insert = userinfoService.insert(user);
        System.out.println("insert = " + insert);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void addUser2(Userinfo user) {
        final int insert = userinfoService.insert(user);
        System.out.println("insert = " + insert);
        throw new RuntimeException();
    }


    /**
     * 场景：嵌套相同事务
     * <p>
     * a) 事务嵌套，在同一个事务中，没有对异常进行处理
     * <p>
     * 执行结果：两个都没有插入成功
     * <p>
     * 结论：由于两个都是在一个事务当中，所以只要有一个方法事务有问题，那么都不会插入成功。
     */
    @Transactional(rollbackFor = Exception.class)
    public void addUser3(Userinfo user) {
        final int insert = userinfoService.insert(user);
        System.out.println("addUser3 insert = " + insert);

        addUser4(Userinfo.builder().userName("tom_31").build());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void addUser4(Userinfo user) {
        final int insert = userinfoService.insert(user);
        System.out.println("addUser4 insert = " + insert);
        throw new RuntimeException();
    }


}
