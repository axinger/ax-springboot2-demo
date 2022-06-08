package com.ax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2022年02月19日 14:56:00
 */

@Service
public class UserService {

    /*
@Transactional(noRollbackFor=Exception.class)

@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW) //使被调用者不受调用者的异常影响,出现异常之后,使父方法回滚,子方法不回滚

@Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS):支持当前事务，如果当前没有事务，就以非事务方式执行。

@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED):如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。(默认)。

@Transactional(rollbackFor = Exception.class,propagation = Propagation.MANDATORY):使用当前的事务，如果当前没有事务，就抛出异常。

@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW):新建事务，如果当前存在事务，把当前事务挂起。

@Transactional(rollbackFor = Exception.class,propagation = Propagation.NOT_SUPPORTED):以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。

@Transactional(rollbackFor = Exception.class,propagation = Propagation.NEVER):以非事务方式执行，如果当前存在事务，则抛出异常。

@Transactional(rollbackFor = Exception.class,propagation = Propagation.NESTED):如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。

@Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS):支持当前事务，如果当前没有事务，就以非事务方式执行。
————————————————
版权声明：本文为CSDN博主「张苏丽」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/weixin_28831341/article/details/113683004
    * */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAll() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from t_user ");

        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    public void upDateAge(String password) {
        String sql = "UPDATE t_user SET password=? WHERE id=?;";
        jdbcTemplate.update(sql, password, 1);
        this.upDateSex(3);
    }

    @Transactional
    public void upDateSex(int sex) {
        String sql = "UPDATE t_user SET sex=? WHERE id=?;";
        jdbcTemplate.update(sql, sex, 1);

        // try 事务失效
//        try {
//            int age = 1 / 0;
//        } catch (Exception e) {
//
//        }

        throw new RuntimeException();

    }

    @Transactional(rollbackFor = Exception.class)
    public void upDateAgeAndSex(String password, int sex) {
        String sql = "UPDATE t_user SET password=? WHERE id=?;";
        jdbcTemplate.update(sql, password, 1);
        this.upDateSex(sex);
    }
}
