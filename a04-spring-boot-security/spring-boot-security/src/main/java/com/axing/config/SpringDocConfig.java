//package com.axing.config;
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.ExternalDocumentation;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import org.springdoc.core.GroupedOpenApi;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
///**
// * SpringDoc API文档相关配置
// * Created by macro on 2022/3/4.
// */
//@Configuration
//
//public class SpringDocConfig {
//
//    @Bean
//    public OpenAPI mallTinyOpenAPI() {
//
//        //信息
//        Info info = new Info()
//                .title("swagger3 测试-标题")
//                .description("这是一段描述：springboot-swagger3")
//                .version("v1.0.0");
//
//        //鉴权组件(随便起名的)
//        SecurityScheme securityScheme = new SecurityScheme()
//                .type(SecurityScheme.Type.HTTP)
//                .scheme("bearer")//固定写法
//                .bearerFormat("JWT")
//                .in(SecurityScheme.In.HEADER)
//                .name("Authorization");
//
//        Components components = new Components()
//                .addSecuritySchemes("bearer-jwt的格式", securityScheme);
//
//        //鉴权限制要求(随便起名的)
//        SecurityRequirement securityRequirement = new SecurityRequirement()
//                .addList("bearer-jwt", Arrays.asList("read", "write"));
//
//        return new OpenAPI()
//                .info(info)
//                .components(components)
//                .addSecurityItem(securityRequirement);
//    }
//
////    @Bean
////    public GroupedOpenApi publicApi() {
////        return GroupedOpenApi.builder()
////                .group("brand")
////                .pathsToMatch("/brand/**")
////                .build();
////    }
////
////    @Bean
////    public GroupedOpenApi adminApi() {
////        return GroupedOpenApi.builder()
////                .group("admin")
////                .pathsToMatch("/admin/**")
////                .build();
////    }
//}
