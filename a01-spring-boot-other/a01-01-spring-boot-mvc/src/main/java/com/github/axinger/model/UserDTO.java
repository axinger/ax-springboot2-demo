package com.github.axinger.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Java 16 新特性：record类
 * record申明的类，具备这些特点：
 * 因为record关键词申明类主要是为了简化一些类的申明，所以它本质就是一类特殊的class，或者说是某一个模版的class。
 * 它是一个final类
 * 自动实现equals、hashCode、toString函数
 * 成员变量均为public属性
 *
 * @param username
 * @param password
 */
@ConfigurationProperties(prefix = "axing.config")
/// @EnableConfigurationProperties(UserDTO.class) 配合使用
public record UserDTO(String username, String password) {

    public String all() {
        return username + password;
    }

    public record range(int start, int end) {

    }

}
