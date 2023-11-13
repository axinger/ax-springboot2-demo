package com.ax;

class MyLog {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MyLog.class);

    private static final org.slf4j.Logger aLog = org.slf4j.LoggerFactory.getLogger("A_SERVICE_LOG");
    private static final org.slf4j.Logger bLog = org.slf4j.LoggerFactory.getLogger("B_SERVICE_LOG");

    public static org.slf4j.Logger getLogger(String name) {
        System.out.println("name = " + name);
        return log;
    }

    public static org.slf4j.Logger getLogger(Class<?> name) {
        System.out.println("name Class = " + name);
        return log;
    }


    public static org.slf4j.Logger getLog2ger(String name) {
        return bLog;
    }

    public void info2(String msg){

    }
}
