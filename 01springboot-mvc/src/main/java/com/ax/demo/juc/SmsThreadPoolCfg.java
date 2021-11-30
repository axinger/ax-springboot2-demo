package com.ax.demo.juc;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sms.threadpool")
public class SmsThreadPoolCfg extends ThreadPoolCfg {

}