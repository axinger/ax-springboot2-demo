package com.ax.demo;

import com.ax.demo.service.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xing
 */
@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
		try {
			new NettyServer(12345).start();
		}catch(Exception e) {
			System.out.println("NettyServerError:"+e.getMessage());
		}
	}
}
