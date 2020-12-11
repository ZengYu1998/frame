package com.quick.frame.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: Swagger配置
 * @author: znegyu
 * @create: 2020-11-23 11:35
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(200)/*.responseModel(new ModelRef("ApiError"))*/.message("操作成功").build());
        responseMessageList.add(new ResponseMessageBuilder().code(400)/*.responseModel(new ModelRef("ApiError"))*/.message("请求出现语法错误").build());
        responseMessageList.add(new ResponseMessageBuilder().code(401)/*.responseModel(new ModelRef("ApiError"))*/.message("访问被拒绝,未经授权").build());
        responseMessageList.add(new ResponseMessageBuilder().code(404)/*.responseModel(new ModelRef("ApiError"))*/.message("找不到资源").build());
        responseMessageList.add(new ResponseMessageBuilder().code(500)/*.responseModel(new ModelRef("ApiError"))*/.message("服务器内部错误").build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                .globalResponseMessage(RequestMethod.HEAD, responseMessageList)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.quick.frame.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .select()
                //是否显示
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());

    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Swagger 实例文档",
                "文档及调联",
                "API V1.0",
                "",
                new Contact("", "", ""),
                "Apache", "http://www.apache.org/", Collections.emptyList());
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList= new ArrayList<>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header: Bearer "));
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts=new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences=new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }
}
