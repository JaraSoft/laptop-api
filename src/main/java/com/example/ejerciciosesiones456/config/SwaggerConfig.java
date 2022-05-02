package com.example.ejerciciosesiones456.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                ;
    }
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Laptop REST Api",
                "Laptop Api for creating and managing laptops",
                "http://example.com/terms-of-service",
                "Terms of service",
                new Contact("Daniel Jaramillo", "https://www.linkedin.com/in/daniel-jaramillo-m", "jarasoftware@gmail.com"),
                "MIT Licence", "http://opensource.org/licences/MIT",
                Collections.emptyList());
    }
}
