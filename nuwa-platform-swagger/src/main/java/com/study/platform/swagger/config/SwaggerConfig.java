package com.study.platform.swagger.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

/**
 * Swagger API相关配置
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    /*需要在配置文件里配置这三个配置*/
    /*配置开启禁用swagger*/
    @Value("${swagger.enabled}")
    private boolean enabled;

    /*配置模块名*/
    @Value("${swagger.groupName}")
    private String groupName;

    /*配置需要扫描的包*/
    @Value("${swagger.basePackage}")
    private String basePackage;

    @Value("${swagger.pathMapping}")
    private String pathMapping;

    @Bean(value = "createRestApi")
    public Docket createRestApi() {
        Set<String> set = new HashSet<>();
        set.add("https");
        set.add("http");
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                //是否启用swagger
                .enable(enabled)
                //.host("http://127.0.0.1:80/nuwa-system")
                //分组名称(包名，模块名)
                //.groupName(groupName)
                .groupName("1.X版本")
                //.useDefaultResponseMessages(false)
                //展示在Swagger页面上的自定义工程描述信息
                .apiInfo(apiInfo())
                .pathMapping(pathMapping)
                //选择展示哪些接口
                .select()
                //配置需要扫描的路径（controller）
                .apis(RequestHandlerSelectors.any())
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //给所有文档都生成文档路径
                .paths(PathSelectors.any())
                .build()
                .protocols(set)
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //描述信息
                .description("Spring Cloud Swagger2 文档")
                //可以通过swagger联系一个人，即联系方式
                .contact(new Contact("wtt", "https://127.0.0.1:8001/doc.html", "864734049@qq.com"))
                //版本
                .version("1.0.0")
                .title("Spring Cloud Swagger2 文档")
                .termsOfServiceUrl("www.nuwa.com")
                .build();
    }

    /**
     * 设置授权信息
     */
    private List<SecurityScheme> securitySchemes() {
        List<ApiKey> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "Header");
        result.add(apiKey);
        return Collections.singletonList(apiKey);
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(Collections.singletonList(new SecurityReference("Authorization", new AuthorizationScope[]{new AuthorizationScope("global", "Authorization")})))
                        .build()
        );
    }
}

