//package com.ax.demo;
//
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.iotdb.session.pool.SessionPool;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@Configuration
//@Slf4j
//@Data
//public class IotDBSessionConfig {
//
//    @Value("${spring.iotdb.username:root}")
//    private String username;
//
//    @Value("${spring.iotdb.password:root}")
//    private String password;
//
//    @Value("${spring.iotdb.ip:127.0.0.1}")
//    private String ip;
//
//    @Value("${spring.iotdb.port:6667}")
//    private int port;
//
//    @Value("${spring.iotdb.maxSize:10}")
//    private int maxSize;
//
//    public static SessionPool sessionPool;
//
//    public SessionPool getSessionPool() {
//        if (sessionPool == null) {
//            sessionPool = new SessionPool(ip, port, username, password, maxSize);
//        }
//
//        return sessionPool;
//    }
//
//    public void insertRecord(String deviceId, long time, List<String> measurements, List<String> values) {
//        getSessionPool();
//        try {
//            log.info("iotdb数据入库：device_id:[{}], measurements:[{}], values:[{}]", deviceId, measurements, values);
//            sessionPool.insertRecord(deviceId, time, measurements, values);
//        } catch (Exception e) {
//            log.error("IotDBSession insertRecord失败: deviceId={}, time={}, measurements={}, values={}, error={}",
//                    deviceId, time, measurements, values, e.getMessage());
//        }
//    }
//
//}
//
//
