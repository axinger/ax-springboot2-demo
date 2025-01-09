package com.github.axinger;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.retry.annotation.EnableRetry;

@ComponentScan(excludeFilters = {
        // 去除装配
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = {
                        com.github.axinger.b.UserService.class
                })
})
@SpringBootApplication
//@EnableRetry(proxyTargetClass = true)
@EnableRetry
@Slf4j
public class A01MVCApplication {

    static String My_Env;

    public static void main(String[] args) {
        SpringApplication.run(A01MVCApplication.class, args);
        log.info("环境变量={}", My_Env);
    }

    @Value("${spring.profiles.active}")
    public void setMyEnv(String myEnv) {
        My_Env = myEnv;
    }


}
