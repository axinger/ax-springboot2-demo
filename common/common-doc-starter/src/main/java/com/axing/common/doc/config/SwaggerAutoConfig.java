//package com.axing.common.swagger.config;
//
//import com.axing.common.swagger.bean.ConfigProperties;
//import com.axing.common.swagger.bean.ContactProperties;
//import io.swagger.v3.oas.models.ExternalDocumentation;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import org.springdoc.core.GroupedOpenApi;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///***
// * @author 阿星
// * @date 2022/3/26
// * @apiNote
// */
//@Configuration
//@EnableConfigurationProperties({
//        ConfigProperties.class,
//        ContactProperties.class
//})
//public class SwaggerAutoConfig {
//
//    @Autowired
//    private ConfigProperties config;
//
//    @Autowired
//    private ContactProperties contact;
//
//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI()
//                .info(
//                        new Info()
//                                .title(config.getTitle())
//                                .description(config.getDescription())
//                                .version("版本号:" + config.getVersion())
////                                .license(new License()
////                                        .name("Apache 2.0")
////                                        .url("https://github.com/macrozheng/mall-learning"))
//                )
//                .externalDocs(new ExternalDocumentation()
//                        .description(contact.getName())
//                        .url(contact.getUrl()));
//    }
//
//    /**
//     * 按照 group 排序
//     *
//     * @return GroupedOpenApi
//     */
//    @Bean
//    public GroupedOpenApi groupedOpenApi() {
//        return GroupedOpenApi.builder().group(config.getGroupName()).pathsToMatch("/**").build();
//    }
//
//}
