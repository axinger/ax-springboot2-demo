package com.ax.demo.config;

import com.ax.demo.model.Dog;
import com.ax.demo.model.Person;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * proxyBeanMethods 是否代理bean方法
 */
//@Import({Dog.class})//导入组件
@Configuration(proxyBeanMethods = true) // 告诉springboot 这是一个配置类
//@ImportResource("classpath:bean1.xml") // 导入bean.xml的类型
public class DemoConfig {

    @Bean // 给容器真能干添加组件,以方法名作为组件id,返回类型就是组件类型
//    @Bean("person02")
    @ConditionalOnBean(name = "dog1") // 有dog 这个,才注入person01
//    @ConditionalOnMissingBean(name = "dog") // 没有xx,才注入
    public Person person01() {
        return new Person("jim", 12);
    }

    @Bean("dog1")
    public Dog dog1() {
        return new Dog("dog_01", 12);
    }

    @ConditionalOnBean(name = "dog2") // 有dog 这个,才注入person01
    public Person person02() {
        return new Person("jim_02", 12);
    }

    @Bean("dog2")
    public Dog dog2() {
        return new Dog("dog_01", 12);
    }
}
