package com.ax.demo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.util.UrlPathHelper;

import java.nio.charset.StandardCharsets;
import java.util.*;


@Configuration
/// 新版本中应该是推荐使用 WebMvcConfigurationSupport
public class DemoWebMvcConfiguration extends WebMvcConfigurationSupport {

    /**
     * 字符返回乱码
     *
     * @return StringHttpMessageConverter
     */
    @Bean
    protected HttpMessageConverter<String> stringHttpMessageConverterUtf8() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    /**
     * json返回工具类及乱码
     *
     * @return HttpMessageConverter
     */

    @Bean
    protected HttpMessageConverter fastJsonHttpMessageConverters() {

        // 2.添加fastjson的配置信息，比如: 是否需要格式化返回的json数据
        FastJsonConfig config = new FastJsonConfig();

        config.setDateFormat("yyyy-MM-dd HH:MM:ss");

        config.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect, //结果是否格式化,默认为false

                SerializerFeature.PrettyFormat, //枚举值使用名称或toString

                SerializerFeature.WriteEnumUsingName, // 保留map空的字段

                SerializerFeature.WriteMapNullValue, // 将String类型的null转成""

                SerializerFeature.WriteNullBooleanAsFalse, // 避免循环引用

                SerializerFeature.WriteNullListAsEmpty, // 将Boolean类型的null转成false

                SerializerFeature.WriteNullNumberAsZero, // 将List类型的null转成[], List<String> list = new ArrayList<>(); 泛型不支持

                SerializerFeature.WriteNullStringAsEmpty // 将Number类型的null转成0
        );


        // 1.定义一个converters转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 3.在converter中添加配置信息
        fastConverter.setFastJsonConfig(config);

        // 4.中文乱码解决方案
        List<MediaType> mediaTypes = new ArrayList<>();
//        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        mediaTypes.add(MediaType.APPLICATION_JSON);

        fastConverter.setSupportedMediaTypes(mediaTypes);

        // 5.返回HttpMessageConverters对象
        return fastConverter;
    }


//    @Bean
//    public WebMvcConfigurer webMvcConfigurer(){
//
//        return new WebMvcConfigurer(){
//
//            @Override
//            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//                WebMvcConfigurer.super.extendMessageConverters(converters);
//
//
//            }
//        };
//    }

    /**
     * 编码配置
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
//        converters.clear();
//        converters.add(stringHttpMessageConverterUtf8());

        converters.add(0, fastJsonHttpMessageConverters());//fastJsonHttpMessageConverters 需要在第一个位置

//        converters.add(new MappingJackson2XmlHttpMessageConverter());
//        converters.add(new MappingJackson2HttpMessageConverter());
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

        /**将static下面的js，css文件加载出来 ,html引入文件就  ⭐️不需要⭐ ../static/ 这样前缀了*/
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


    /**
     * 拦截器配置
     */
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/goLogin", "/login");
//    }
    @Override
    protected void configurePathMatch(PathMatchConfigurer configurer) {


        super.configurePathMatch(configurer);

        /**开启矩阵变量*/
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);

        configurer.setUrlPathHelper(urlPathHelper);

    }

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);
        converters.add(new DemoMessageConverter());
    }

    /**
     * 自定义内容协商策略
     */
    @Override
    protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        super.configureContentNegotiation(configurer);

        Map<String, MediaType> map = new HashMap<>();

        map.put("jsom", MediaType.APPLICATION_JSON);
        map.put("xml", MediaType.APPLICATION_XML);
        map.put("gg", MediaType.parseMediaType("application/x-axing"));

        // 参数
        ParameterContentNegotiationStrategy strategy = new ParameterContentNegotiationStrategy(map);

        // 头部
        HeaderContentNegotiationStrategy headerStrategy = new HeaderContentNegotiationStrategy();


        configurer.strategies(Arrays.asList(strategy, headerStrategy));


//        configurer.favorPathExtension(true) //是否支持后缀的方式
//                .parameterName("mediaType")
//                .defaultContentType(MediaType.APPLICATION_JSON)
//                .mediaType("xml", MediaType.APPLICATION_XML)   //当后缀名称为xml的时候返回xml数据
//                .mediaType("html", MediaType.TEXT_HTML)              //当后缀名称是html时候返回html页面
//                .mediaType("json", MediaType.APPLICATION_JSON);//当后缀名称是json的时候返回json数据

    }

}
