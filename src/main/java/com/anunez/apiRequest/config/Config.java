package com.anunez.apiRequest.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;    
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class Config {

    @Bean
    public Docket api (){
        return  new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.anunez.apiRequest.controller"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Test Api Request",
                "Rest Api utilizado para apificar la web https://www.hoy.com.py/.",
                "v1",
                "Terms of service",
                new Contact("Adam Nunez", "www.adamnunez.com", "adamaguire96@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
    
}