//package com.ax.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
////@Profile("dev")
//@Configuration
////@EnableConfigurationProperties(SwaggerInfo.class) //为了 解决 第三方 包,没有写 @Component
//@Profile("dev")
//public class Swagger3Config {
//
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo())
//                // 是否开启
////                .enable(swaggerEnabled)
//                .select()
//                // 扫描的路径包
//                .apis(RequestHandlerSelectors.basePackage("com.ax.demo.controller"))
////                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                // 指定路径处理PathSelectors.any()代表所有的路径
//
//                .paths(PathSelectors.any())
//                .build()
//                .groupName("我的组");
////                .pathMapping("/");
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("SpringBoot-Swagger3集成和使用-demo示例")
//                .description("springboot | swagger")
//                // 作者信息
//                .contact(new Contact("name", "个人主页url", "email"))
//                .version("1.0.0")
//                .build();
//    }
//}
