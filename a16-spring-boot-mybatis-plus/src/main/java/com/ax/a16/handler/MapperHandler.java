package com.ax.a16.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName MapperHandler.java
 * @Description TODO
 * @createTime 2022年01月23日 23:27:00
 * mp 自动填充
 */
@Component
public class MapperHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

//        this.setFieldValByName("createTime", new Date(), metaObject);
//        this.setFieldValByName("updateTime", new Date(), metaObject);
//        this.setFieldValByName("version", 1, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
