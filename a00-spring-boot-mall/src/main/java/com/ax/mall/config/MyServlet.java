package com.ax.mall.config;

import com.ax.mall.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyServlet {

//    public ServletRegistrationBean myServlet(){
//
//    }

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new MyFilter());
        registrationBean.addUrlPatterns("/");
        return registrationBean;
    }
}
