package com.github.axinger.config;

import com.github.axinger.model.bean.ApplicationInfo;
import com.github.axinger.model.bean.HumitureRuleProperties;
import com.github.axinger.model.bean.MyYmlBean;
import com.github.axinger.model.bean.PersonProperties;
import com.github.axinger.model.bean.UserProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
//@Configuration
@EnableConfigurationProperties(value = {
        UserProperties.class,
        PersonProperties.class,
        HumitureRuleProperties.class,
        MyYmlBean.class,
        ApplicationInfo.class
})
public class MyAutoImportConfig {

}
