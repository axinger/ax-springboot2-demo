package com.axing.demo27;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc API文档相关配置
 * Created by macro on 2022/3/4.
 */
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI mallTinyOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Mall-Tiny API")
                                .description("SpringDoc API 演示")
                                .version("v1.0.0")
                                .license(new License()
                                        .name("Apache 2.0")
                                        .url("https://github.com/macrozheng/mall-learning")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringBoot实战电商项目mall（50K+Star）全套文档")
                        .url("http://www.macrozheng.com"));
    }

//    /**
//     * 按照 group 排序
//     *
//     * @return GroupedOpenApi
//     */
//    @Bean
//    public GroupedOpenApi publicApi() {
//        return GroupedOpenApi.builder().group("brand").pathsToMatch("/brand/**").build();
//    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder().group("admin").pathsToMatch("/**").build();
    }
}
