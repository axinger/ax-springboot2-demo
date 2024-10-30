package com.github.axinger.config;

import com.github.axinger.bean.ApplicationInfo;
import com.github.axinger.bean.HumitureRuleProperties;
import com.github.axinger.bean.MyYmlBean;
import com.github.axinger.model.Person;
import com.github.axinger.model.UserVO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
@EnableConfigurationProperties(value = {
        UserVO.class,
        Person.class,
        HumitureRuleProperties.class,
        MyYmlBean.class,
        ApplicationInfo.class
})
public class MyAutoImportConfig {

}
