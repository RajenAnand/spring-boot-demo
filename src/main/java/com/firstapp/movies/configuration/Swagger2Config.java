package com.firstapp.movies.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.firstapp.movies.controller.MoviesListController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	
    private ApiInfo metadata() {
        return new ApiInfoBuilder().title("Movies API").description("API reference guide for developers")
                .termsOfServiceUrl("https://www.comelearn.com/").contact(new Contact("", "", "reach@comelearn.com"))
                .version("1.0").build();
    }
 
    @Bean
    public Docket itemApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Movies API").apiInfo(metadata()).select()
                .paths(PathSelectors.regex("/movies/.*")).build();
 
    }
 
}
