package com.axing.common.swagger.config;

import com.axing.common.swagger.bean.ConfigProperties;
import com.axing.common.swagger.bean.ContactProperties;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * @author 阿星
 * @date 2022/3/26
 * @apiNote
 */
@Configuration
@EnableConfigurationProperties({
        ConfigProperties.class,
        ContactProperties.class
})
public class SwaggerAutoConfig {

    @Autowired
    private ConfigProperties config;

    @Autowired
    private ContactProperties contact;

//    /**
//     * 配置文档生成最佳实践
//     *
//     * @param apiInfo
//     * @return
//     */
//    @Bean
//    public Docket createRestApi(ApiInfo apiInfo) {
//        return new Docket(DocumentationType.OAS_30)
//                .enable(config.getEnable())
//                .apiInfo(apiInfo)
//                .groupName(config.getGroupName())
//                .select()
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    /**
//     * 配置基本信息
//     *
//     * @return
//     */
//    @Bean
//    public ApiInfo apiInfo() {
//
//        return new ApiInfoBuilder()
//                // 设置标题
//                .title(config.getTitle())
//                // 描述
//                .description(config.getDescription())
//                // 作者信息
//                .contact(new Contact(contact.getName(),
//                        contact.getUrl(),
//                        contact.getEmail()))
//                // 版本
//                .version("版本号:" + config.getVersion())
//                .build();
//    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title(config.getTitle())
                                .description(config.getDescription())
                                .version("版本号:" + config.getVersion())
//                                .license(new License()
//                                        .name("Apache 2.0")
//                                        .url("https://github.com/macrozheng/mall-learning"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description(contact.getName())
                        .url(contact.getUrl()));
    }

    /**
     * 按照 group 排序
     *
     * @return GroupedOpenApi
     */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group(config.getGroupName()).pathsToMatch("/**").build();
    }

}
