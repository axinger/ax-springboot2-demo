package com.axing.demo.bean;

import com.axing.common.util.factory.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

// @Data
// // @Configuration
// @ConfigurationProperties(prefix = "my")
// @PropertySource(value = {"classpath:my.yml"}, factory = YamlAndPropertySourceFactory.class)
// public class MyProperties {
//     private User user;
//     private List<User> list;
//
//     /**
//      * record 和顺序没有关系,要对应名称. 且不用static修饰
//      *
//      * @param password
//      * @param username
//      */
//     public record User(String password, String username) {
//
//     }
//
// }

@ConfigurationProperties(prefix = "my")
@EnableConfigurationProperties(MyProperties.class) // 这句重要,要重复2次
@PropertySource(value = {"classpath:my.yml"}, factory = YamlPropertySourceFactory.class) // 需要自定义yaml解析
public record MyProperties(User user, List<User> list) {
    /**
     * record 和顺序没有关系,要对应名称. 且不用static修饰
     *
     * @param password
     * @param username
     */
    public record User(String password, String username) {

    }

}
