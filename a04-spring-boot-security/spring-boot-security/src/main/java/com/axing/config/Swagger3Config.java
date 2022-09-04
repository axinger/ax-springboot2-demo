package com.axing.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Swagger3"
                , version = "1.0"
                , description = "Swagger3使用演示"
//                , contact = @Contact(name = "TOM", url = "https://www.baidu.com/", email = "abc@qq.com")
        )
        , security = @SecurityRequirement(name = "JWT")
        , externalDocs = @ExternalDocumentation(description = "参考文档"
        , url = "https://www.baidu.com/"
)
)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "JWT", scheme = "Bearer", in = SecuritySchemeIn.HEADER)
public class Swagger3Config {

}
