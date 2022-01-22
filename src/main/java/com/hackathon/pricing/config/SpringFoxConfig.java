package com.hackathon.pricing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Set;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
@EnableSwagger2
class SpringFoxConfig {

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = Set.of(MediaType.APPLICATION_JSON_VALUE);

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .select()
                .apis(basePackage("com.hackathon.pricing.controller"))
                .paths(any())
                .build();
    }
}
