package com.github.axinger.model.bean;

import com.axing.common.util.factory.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@ConfigurationProperties(prefix = "my")
@PropertySource(value = {"classpath:my.yml"}, factory = YamlPropertySourceFactory.class) // 需要自定义yaml解析
public record MyYmlBean(User user, List<User> list) {
//    /**
//     * record 和顺序没有关系,要对应名称. 且不用static修饰
//     *
//     * @param password
//     * @param username
//     * @param tip      默认逗号会自动截取, @Value("#{'${tip}'.split(',')}")
//     */
//    public record User(String password, String username, @Value("#{'${tip}'.split('~')}") List<String> tip) {
//
//    }

    @Data
    public static class User {
        private String username;
        private String password;
        private List<String> tip;
    }
}
