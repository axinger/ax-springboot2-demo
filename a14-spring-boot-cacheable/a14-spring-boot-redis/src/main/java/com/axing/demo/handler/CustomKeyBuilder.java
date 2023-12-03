package com.axing.demo.handler;

import com.baomidou.lock.DefaultLockKeyBuilder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义分布式锁key生成器
 *
 * @author: austin
 * @since: 2023/3/15 15:46
 */
@Component
public class CustomKeyBuilder extends DefaultLockKeyBuilder {

    public CustomKeyBuilder(BeanFactory beanFactory) {
        super(beanFactory);
    }
}
