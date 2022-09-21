package com.axing.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.StandardCharsets;

@Configuration
public class MyWebMvcConfigurationSupport extends WebMvcConfigurationSupport {

    /**
     * 字符返回乱码
     *
     * @return StringHttpMessageConverter
     */
    @Bean
    public HttpMessageConverter<String> stringHttpMessageConverterUtf8() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }


    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        super.configureContentNegotiation(configurer);
        configurer.favorPathExtension(false);
    }


    /**
     * 将.html 添加 到 resources目录下
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        super.addResourceHandlers(registry);

        /**将static下面的js，css文件加载出来 ,html引入文件就需要 ../static/ 这样前缀了*/
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        /// 使用这个,html引入文件就不需要 ../static/ 这样前缀了
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");


    }
}
