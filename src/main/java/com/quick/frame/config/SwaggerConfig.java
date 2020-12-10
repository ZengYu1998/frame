package com.quick.frame.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
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
                .apiInfo(apiInfo());

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
}
