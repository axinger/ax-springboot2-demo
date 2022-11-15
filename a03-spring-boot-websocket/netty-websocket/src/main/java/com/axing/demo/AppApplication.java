package com.axing.demo;

import com.axing.demo.service.NettyWebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xing
 */
//@SpringBootApplication
//public class AppApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(AppApplication.class, args);
//		try {
//			new NettyServer(12345).start();
//		}catch(Exception e) {
//			System.out.println("NettyServerError:"+e.getMessage());
//		}
//	}
//}

/**
 * @dete: 2021/4/21 9:08 上午
 * @author: 徐子木
 */
@SpringBootApplication
public class AppApplication implements CommandLineRunner {

    // yml中指定netty端口号
    @Value("${netty.port}")
    private int nettyServerPort;

    @Autowired
    private NettyWebSocketServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        //netty 服务端启动的端口不可和Springboot启动类的端口号重复
        nettyServer.start(nettyServerPort);

        //关闭服务器的时候同时关闭Netty服务
        Runtime.getRuntime().addShutdownHook(new Thread(() -> nettyServer.destroy()));
    }
}





