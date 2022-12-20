package com.axing.common.advice.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "axing.advice")
public class AdviceProperties {
    /**
     * 错误是否,打印
     */
    private boolean printStackTrace = false;
}
