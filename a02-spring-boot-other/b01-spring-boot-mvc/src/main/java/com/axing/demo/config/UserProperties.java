package com.axing.demo.config;

import com.axing.common.util.factory.YamlAndPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "user")
@PropertySource(value = {"classpath:user.yml"}, factory = YamlAndPropertySourceFactory.class)
public class UserProperties {
    private User user;

    private List<User> list;

    // @Data
    // public static class User {
    //     private String username;
    //     private String password;
    // }

    /**
     * record 和顺序没有关系,要对应名称. 且不用static修饰
     *
     * @param password
     * @param username
     */
    public record User(String password, String username) {

    }

}
