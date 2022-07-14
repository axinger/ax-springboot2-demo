package com.axing.common.swagger.config;

import com.axing.common.swagger.bean.ConfigProperties;
import com.axing.common.swagger.bean.ContactProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/***
 * @author 阿星
 * @date 2022/3/26
 * @apiNote
 */
@EnableOpenApi
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

    /**
     * 配置文档生成最佳实践
     *
     * @param apiInfo
     * @return
     */
    @Bean
    public Docket createRestApi(ApiInfo apiInfo) {
        return new Docket(DocumentationType.OAS_30)
                .enable(config.getEnable())
                .apiInfo(apiInfo)
                .groupName(config.getGroupName())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 配置基本信息
     *
     * @return
     */
    @Bean
    public ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                // 设置标题
                .title(config.getTitle())
                // 描述
                .description(config.getDescription())
                // 作者信息
                .contact(new Contact(contact.getName(),
                        contact.getUrl(),
                        contact.getEmail()))
                // 版本
                .version("版本号:" + config.getVersion())
                .build();
    }
}
