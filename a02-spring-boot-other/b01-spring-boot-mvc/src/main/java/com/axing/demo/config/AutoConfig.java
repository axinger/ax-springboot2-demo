package com.axing.demo.config;

import com.axing.demo.bean.HumitureRuleProperties;
import com.axing.demo.bean.MyProperties;
import com.axing.demo.model.Person;
import com.axing.demo.model.UserDTO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
@EnableConfigurationProperties(value = {
        UserDTO.class,
        Person.class,
        HumitureRuleProperties.class,
        MyProperties.class
})
public class AutoConfig {
}
