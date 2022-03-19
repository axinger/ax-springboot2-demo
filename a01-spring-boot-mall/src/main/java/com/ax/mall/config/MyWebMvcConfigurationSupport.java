package com.ax.mall.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ax.mall.inteceptor.BaseInteceptor;
import com.ax.mall.inteceptor.LogInterceptor;
import com.ax.mall.inteceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyWebMvcConfigurationSupport extends WebMvcConfigurationSupport {

    /**
     * @Configuration 中所有带 @Bean 注解的方法都会被动态代理，
     * 因此调用该方法返回的都是同一个实例（为什么会返回同一个实例呢，因为调用该方法是会首先判断时候已经通过cglib代理创建了实例，
     * 如果已经创建好了的话，则返回当前的实例）。
     */
    @Bean
    public BaseInteceptor baseInteceptor() {
        return new BaseInteceptor();
    }

    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

    /**
     * 字符返回乱码
     *
     * @return
     */
    @Bean
    public HttpMessageConverter<String> stringHttpMessageConverterUtf8() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }


    /**
     * json返回工具类及乱码
     *
     * @return
     */
    @Bean
    public HttpMessageConverter fastJsonHttpMessageConverters() {

        // 2.添加fastjson的配置信息，比如: 是否需要格式化返回的json数据
        FastJsonConfig config = new FastJsonConfig();

        config.setDateFormat("yyyy-MM-dd HH:MM:ss");

        config.setSerializerFeatures(

                //结果是否格式化,默认为false
                SerializerFeature.PrettyFormat,
                //枚举值使用名称或toSting
                SerializerFeature.WriteEnumUsingName,
                // 保留map空的字段
                SerializerFeature.WriteMapNullValue,
                // 将String类型的null转成""
                SerializerFeature.WriteNullStringAsEmpty,
                // 将Number类型的null转成0
                SerializerFeature.WriteNullNumberAsZero,
                // 将List类型的null转成[], List<String> list = new ArrayList<>(); 泛型不支持
                SerializerFeature.WriteNullListAsEmpty,
                // 将Boolean类型的null转成false
                SerializerFeature.WriteNullBooleanAsFalse,
                // 避免循环引用
                SerializerFeature.DisableCircularReferenceDetect);


        // 1.定义一个converters转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 3.在converter中添加配置信息
        fastConverter.setFastJsonConfig(config);


        // 4.中文乱码解决方案
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        fastConverter.setSupportedMediaTypes(mediaTypes);

        // 5.返回HttpMessageConverters对象
        return fastConverter;
    }


    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个
//        registry.addInterceptor(baseInteceptor()).addPathPatterns("/**");

//        registry.addInterceptor(tokenInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/","/login.do","/register.do","/home.page");


//        registry.addInterceptor(logInterceptor()).addPathPatterns("/**");
//        不需要拦截的
//        String[] excludePathPatterns = {};

//        registry.addInterceptor(this.tokenInterceptor()).addPathPatterns("/**").excludePathPatterns();
        super.addInterceptors(registry);

    }


    /**
     * 将.html 添加 到 resources目录下
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {


        /**将static下面的js，css文件加载出来 ,html引入文件就  ⭐需要⭐ ../static/ 这样前缀了*/
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        /** 将static下面的js，css文件加载出来 ,html引入文件就  ⭐️不需要⭐ ../static/ 这样前缀了*/
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/")
                .addResourceLocations("classpath:/images/");


        /** 自定义图片文件夹 */
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/images/");


//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//
//        registry.addResourceHandler("/web_frontend/**").addResourceLocations("classpath:/web_frontend/");
        //解决办法就是设置自定义static路径的时候，不要使用/**，而是自己给加一个前缀

//        registry.addResourceHandler("/upload/**").addResourceLocations("file:/Users/axing/Desktop/UploadData/images");

//        registry.addResourceHandler("/upload/**").addResourceLocations("classpath:/upload/");


        registry.addResourceHandler("/**.html")
                .addResourceLocations("classpath:/META-INF/resources/", "/static", "/templates");


        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");

        super.addResourceHandlers(registry);
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);

        converters.add(stringHttpMessageConverterUtf8());
        converters.add(fastJsonHttpMessageConverters());

        converters.add(0, new MappingJackson2HttpMessageConverter());
    }

    /**
     * 解决 RestControllerAdvice 返回string时候报错
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);
        converters.removeIf(httpMessageConverter -> httpMessageConverter instanceof StringHttpMessageConverter);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        super.configureContentNegotiation(configurer);
        configurer.favorPathExtension(false);
    }

    /**
     * 用来 ApiVersion 进行控制的,如不添加,则项目无法启动
     */
    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new MyRequestMappingHandlerMapping();
    }

    /**
     * 全局跨域
     * https://www.cnblogs.com/antLaddie/p/14751540.html
     * 五种解决方式：
     * ①：返回新的CorsFilter
     * ②：重写WebMvcConfigurer
     * ③：使用注解@CrossOrigin
     * ④：手动设置响应头（HttpServletResponse）参考第一章第四节注意：
     * CorFilter / WebMvConfigurer / @CrossOrigin 需要 SpringMVC 4.2以上版本才支持，对应springBoot 1.3版本以上
     * 上面前两种方式属于全局 CORS 配置，后两种属于局部 CORS配置。如果使用了局部跨域是会覆盖全局跨域的规则，所以可以通过 @CrossOrigin 注解来进行细粒度更高的跨域资源控制。
     * 其实无论哪种方案，最终目的都是修改响应头，向响应头中添加浏览器所要求的数据，进而实现跨域
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);

        //添加映射路径
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //设置放行哪些原始域   SpringBoot2.4.4下低版本使用.allowedOrigins("*")
//                .allowedOriginPatterns("*")
                .allowedOrigins("*")
                //放行哪些请求方式
//                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                .allowedMethods("*")
                //放行哪些原始请求头部信息
                .allowedHeaders("*")
                //暴露哪些原始请求头部信息
                .exposedHeaders("*")
                /**跨域允许时间;*/
                .maxAge(3600);
    }
}
