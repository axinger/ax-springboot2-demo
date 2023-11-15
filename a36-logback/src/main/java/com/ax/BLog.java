package com.ax;

import org.slf4j.LoggerFactory;

public class BLog {

    private static final org.slf4j.Logger bLog = LoggerFactory.getLogger("B_SERVICE_LOG");


    public void info(String format, Object... args) {
        bLog.info(format, args);
    }
}
