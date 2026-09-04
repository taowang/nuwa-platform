package com.study.nuwa.platform.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * springdoc-openapi 配置（替代原 springfox + knife4j 3.x 方案）。
 * <p>
 * knife4j 4.x 基于 springdoc 自动装配，无需 {@code @Enable*} 注解。
 * 通过 {@code springdoc.swagger-ui.path} / {@code knife4j.enable} 在 application.yml 控制开关。
 */
@Configuration
@ConditionalOnProperty(name = "swagger.enabled", havingValue = "true", matchIfMissing = true)
public class SwaggerConfig {

    @Value("${swagger.groupName:nuwa}")
    private String groupName;

    @Bean
    public OpenAPI nuwaOpenAPI() {
        final String securitySchemeName = "Authorization";
        return new OpenAPI()
                .info(new Info()
                        .title("nuwa 接口文档 - " + groupName)
                        .description("Nuwa 微服务接口文档（springdoc-openapi）")
                        .version("1.0.0")
                        .contact(new Contact().name("wtt").email("864734049@qq.com"))
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .scheme("Bearer")));
    }
}
