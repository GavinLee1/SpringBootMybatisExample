package com.study;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;

@Configuration//TODO:Study how it is working.
@EnableSwagger2//TODO:Study how it is working.
public class SwaggerConfig {
	//TODO: Study what is Docket
	public Docket swaggerSpringMvcPlugin() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any()).paths(Predicates.not(PathSelectors.regex("/error")))// Exclude Spring error controllers
				.build()
				.apiInfo(apiInfo()).directModelSubstitute(Timestamp.class, Long.class)
				.directModelSubstitute(LocalDateTime.class, java.sql.Date.class)
				.directModelSubstitute(LocalDateTime.class, java.util.Date.class)
				.useDefaultResponseMessages(false);
	}
	
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder()
				.title("Spring Boot And Mybatis Study Demo")
				.description("This is a demo project for myself to study how to integrate mybatis.")
				.contact("Gavin")
				.license("Mozat")
				.licenseUrl("http://loopslive.com")
				.version("0.0.1")
				.build();
		return apiInfo;
	}

}
