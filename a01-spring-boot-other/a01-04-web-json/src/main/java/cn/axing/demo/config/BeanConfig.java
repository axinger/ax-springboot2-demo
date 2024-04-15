package cn.axing.demo.config;

import com.axing.demo.entity.Pig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public Pig pig() {
        Pig pig = new Pig();
        System.out.println("pig = " + pig);
        return pig;
    }
}
