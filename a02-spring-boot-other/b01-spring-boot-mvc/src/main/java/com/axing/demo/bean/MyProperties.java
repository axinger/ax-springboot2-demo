package com.axing.demo.bean;

import com.axing.common.util.factory.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Data
// @Configuration
@ConfigurationProperties(prefix = "my")
// 需要自定义yaml解析
@PropertySource(value = {"classpath:my.yml"}, factory = YamlPropertySourceFactory.class)
public class MyProperties {
    private User user;
    private List<User> list;
    /**
     * record 和顺序没有关系,要对应名称. 且不用static修饰
     *
     * @param password
     * @param username
     */
    public record User(String password, String username) {

    }

}
