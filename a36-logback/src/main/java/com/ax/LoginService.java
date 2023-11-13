package com.ax;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
//@CustomLog(topic = "")
//@CommonsLog(topic="CounterLog")
//@Log(topic="CounterLog")
@Slf4j(topic = "aaa")
public class LoginService {
    private static final org.slf4j.Logger aLog = org.slf4j.LoggerFactory.getLogger("A_SERVICE_LOG");
    private static final org.slf4j.Logger bLog = org.slf4j.LoggerFactory.getLogger("B_SERVICE_LOG");
//    private static final org.slf4j.Logger bLog = org.slf4j.LoggerFactory.getLogger(LoginService.class);

    public void login() {
        log.info("普通日志");
        aLog.info("登录日志=======================");
        bLog.info("注册日志=======================");
    }
}
