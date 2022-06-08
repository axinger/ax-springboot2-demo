package com.ax.demo.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 不推荐直接继承WebMvcConfigurationSupport ，
 * 使用默认配置EnableWebMvcConfiguration即可，如果一定要这样用，可继承 EnableWebMvcConfiguration/DelegatingWebMvcConfiguration
 * 添加自定义拦截器，消息转换器推荐实现WebMvcConfigurer接口方式
 * <p>
 * 最后如果只是要自定义HttpMessageConverter
 */
@Configuration
/// 新版本中应该是推荐使用 WebMvcConfigurationSupport 有很多不确定,
// 还是使用 WebMvcConfigurer
public class WebConfig implements WebMvcConfigurer {


//    @Autowired
//    private FastJsonHttpMessageConverter fastJsonHttpMessageConverter;
//
//    @Autowired
//    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    //    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //registry.addResourceHandler("/static/*/**").addResourceLocations("classpath:/static/");
//        //重写这个方法，映射静态资源文件
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/resources/")
//                .addResourceLocations("classpath:/static/")
//                .addResourceLocations("classpath:/public/")
//        ;
//        super.addResourceHandlers(registry);
//
//    }
//
    @Autowired
    private FastJsonHttpMessageConverter fastJsonHttpMessageConverter;


    /**
     * 编码配置
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);

        JSON.DEFAULT_GENERATE_FEATURE = SerializerFeature.config(
                JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.SkipTransientField, false);

        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM月-dd日 HH:MM:ss";

        converters.add(0, fastJsonHttpMessageConverter);
    }


    /**
     * 将.html 添加 到 resources目录下
     *
     * @param registry
     */
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//
//        /**将static下面的js，css文件加载出来 ,html引入文件就  ⭐需要⭐ ../static/ 这样前缀了*/
////        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//
//        /**将static下面的js，css文件加载出来 ,html引入文件就  ⭐️不需要⭐ ../static/ 这样前缀了*/
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/resources/")
//                .addResourceLocations("classpath:/static/")
//                .addResourceLocations("classpath:/public/")
//                .addResourceLocations("classpath:/images/");
//
//
//        /** 自定义图片文件夹 */
//        registry.addResourceHandler("/img/**")
//                .addResourceLocations("classpath:/images/");
//
//
////        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
////
////        registry.addResourceHandler("/web_frontend/**").addResourceLocations("classpath:/web_frontend/");
//        //解决办法就是设置自定义static路径的时候，不要使用/**，而是自己给加一个前缀
//
////        registry.addResourceHandler("/upload/**").addResourceLocations("file:/Users/axing/Desktop/UploadData/images");
//
////        registry.addResourceHandler("/upload/**").addResourceLocations("classpath:/upload/");
//
//
//        registry.addResourceHandler("/**.html")
//                .addResourceLocations("classpath:/META-INF/resources/", "/static", "/templates");
//
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//
//        registry.addResourceHandler("/swagger-ui/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
//
//        super.addResourceHandlers(registry);
//    }
//

    /**
     * 拦截器配置
     */
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/goLogin", "/login");
//    }


    /**
     * 发现如果继承了 WebMvcConfigurationSupport 则在yml中配置的相关内容会失效。
     * 需要重新指定静态资源
     *
     * @param registry
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//        super.addResourceHandlers(registry);
//    }


    /**
     * 配置servlet处理
     */
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
}
