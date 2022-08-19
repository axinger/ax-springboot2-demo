package com.axing.common.doc.config;

import com.axing.common.doc.bean.DocInfo;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties({
        DocInfo.class
})
public class SpringDocConfig {
    private final DocInfo docInfo;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title(docInfo.getTitle()).description(docInfo.getDescription()).version(docInfo.getVersion())).externalDocs(new ExternalDocumentation().description(docInfo.getWebsiteName()).url(docInfo.getWebsiteUrl()));
    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder().group(docInfo.getTitle()).pathsToMatch("/**").build();
    }
}