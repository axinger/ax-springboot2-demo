package com.ax;

import org.slf4j.LoggerFactory;

class MyLog {
    private static final org.slf4j.Logger aLog = org.slf4j.LoggerFactory.getLogger("A_SERVICE_LOG");
    public final BLog bLog = new BLog();
    private final org.slf4j.Logger log;
//    private static final org.slf4j.Logger bLog = org.slf4j.LoggerFactory.getLogger("B_SERVICE_LOG");

    @SuppressWarnings("all")
    private MyLog(String name) {
        this.log = LoggerFactory.getLogger(name);
    }

    private MyLog(Class<?> clazz) {
        this.log = LoggerFactory.getLogger(clazz);
    }

    @SuppressWarnings("all")
    public static MyLog getLogger(Class<?> clazz) {
        return new MyLog(clazz);
    }

    public void info(String format, Object... args) {
        log.info(format, args);
    }

    public void aInfo(String format, Object... args) {
        aLog.info(format, args);
    }


}
