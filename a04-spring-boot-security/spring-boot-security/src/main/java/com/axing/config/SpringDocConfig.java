package com.axing.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * SpringDoc API文档相关配置
 * Created by macro on 2022/3/4.
 */
@Configuration
public class SpringDocConfig {
    private static final String SECURITY_SCHEME_NAME = "Authorization";

    @Bean
    public OpenAPI mallTinyOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("权限控制")
//                        .description("SpringDoc API 演示")
//                        .version("v1.0.0")
//                        .license(new License().name("Apache 2.0").url("https://github.com/macrozheng/mall-learning")))
//                .externalDocs(new ExternalDocumentation()
//                        .description("SpringBoot实战电商项目mall（50K+Star）全套文档")
//                        .url("http://www.macrozheng.com"))
//                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
//                .components(new Components()
//                                .addSecuritySchemes(SECURITY_SCHEME_NAME,
//                                        new SecurityScheme()
//                                                .name(SECURITY_SCHEME_NAME)
//                                                .type(SecurityScheme.Type.HTTP)
//                                                .scheme("bearer+")
//                                                .bearerFormat("JWT")));

        //信息
        Info info = new Info()
                .title("swagger3 测试-标题")
                .description("这是一段描述：springboot-swagger3")
                .version("v1.0.0");

        //鉴权组件(随便起名的)
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer+")//固定写法
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        Components components = new Components()
                .addSecuritySchemes("bearer-jwt", securityScheme);

        //鉴权限制要求(随便起名的)
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("bearer-jwt", Arrays.asList("read", "write"));

        return new OpenAPI()
                .info(info)
                .components(components)
                .addSecurityItem(securityRequirement);
    }

}
