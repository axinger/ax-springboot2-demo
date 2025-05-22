package com.github.axinger.config;

import com.google.common.net.InetAddresses;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;

@Component
@Order(value = 1)
@Slf4j
public class Start1 implements ApplicationRunner {

    @Autowired
    private InetUtils inetUtils;

    @Autowired
    private ServletWebServerApplicationContext context;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner启动监控=" + args);


        //查找第一个非回环的主机信息
        /*
        非回环主机信息指的是：

        排除回环地址(loopback address，如127.0.0.1)

        排除未启用的网络接口

        通常指计算机的实际网络接口(如以太网、WiFi等)的IP地址
         */
        InetUtils.HostInfo hostInfo = inetUtils.findFirstNonLoopbackHostInfo();
        String hostname = hostInfo.getHostname();
        String ip = hostInfo.getIpAddress();
        log.info("本机ip={}", ip);
        log.info("本机hostname={}", hostname);



        int port = context.getWebServer().getPort();
        System.out.println("port = " + port);
        String ip2 = InetAddress.getLocalHost().getHostAddress();
        System.out.println("ip2 = " + ip2);


//        String host = context.getWebServer().getAddress().getHostString();
//        System.out.println("Server is running at http://" + host + ":" + port);



        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
//            System.out.println("Interface: " + networkInterface.getName());

            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress address = addresses.nextElement();
//                System.out.println("  Address: " + address.getHostAddress());
//                System.out.println("  Address: " + address.getHostAddress());

                if (!address.isLoopbackAddress() && address instanceof Inet4Address) {
                    System.out.println("  Address: " + address.getHostAddress());
                }
            }
        }
    }

    @SneakyThrows
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady(ApplicationReadyEvent event) {
        System.out.println("Spring Boot 应用已完全启动并准备好接收请求");

        // 获取Environment实例
        Environment env = context.getEnvironment();

        // 获取端口号和上下文路径
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path", "");

        // 打印启动信息
        System.out.println("\n\n===========> 系统启动成功！后台地址：http://localhost:" + serverPort + contextPath);


        Environment environment = context.getBean(Environment.class);
        String path = environment.getProperty("server.servlet.context-path")==null?
                "":environment.getProperty("server.servlet.context-path");
        String port = environment.getProperty("server.port");
        String ip = InetAddress.getLocalHost().getHostAddress();
        log.info("Access URLs:\n----------------------------------------------------------\n\t"
                        + "Local: \t\thttp://127.0.0.1:{}{}\n\t"
                        + "External: \thttp://{}:{}{}\n----------------------------------------------------------",
                port, path, ip, port, path
        );
    }

    @EventListener(ServletWebServerInitializedEvent.class)
    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        System.out.println("Application is running on port: " + port);
    }

}
