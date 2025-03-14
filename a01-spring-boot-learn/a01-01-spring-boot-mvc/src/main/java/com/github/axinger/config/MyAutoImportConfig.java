package com.github.axinger.config;

import com.github.axinger.model.bean.*;
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
