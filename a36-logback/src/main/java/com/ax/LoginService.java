package com.ax;

import lombok.CustomLog;
import org.springframework.stereotype.Service;

@Service
@CustomLog
//@CommonsLog(topic="CounterLog")
//@Log(topic="CounterLog")
//@Slf4j(topic = "aaa")
//@Slf4j
public class LoginService {
//    private static final org.slf4j.Logger aLog = org.slf4j.LoggerFactory.getLogger("A_SERVICE_LOG");
//    private static final org.slf4j.Logger bLog = org.slf4j.LoggerFactory.getLogger("B_SERVICE_LOG");
//    private static final org.slf4j.Logger bLog = org.slf4j.LoggerFactory.getLogger(LoginService.class);

    public void login() {
        log.info("普通日志");
//        aLog.info("登录日志=======================");
//        bLog.info("注册日志=======================");
//        MDC.put("business", "B_SERVICE_INFO");

        log.aInfo("登录日志=======================");

//        log.bInfo("注册日志=======================");

        log.bLog.info("注册日志=======================");

//        MDC.put("business", "B_SERVICE_INFO");
//        log.info("B注册日志=======================");
//        // Clear the business context
//        MDC.remove("business");
    }
}
