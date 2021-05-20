package com.ax.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value=1)
public class StartupRunnerOne implements CommandLineRunner{
	@Override
	public void run(String... args) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>服务启动第一个开始执行的任务，执行加载数据等操作<<<<<<<<<<<<<");
	}
}