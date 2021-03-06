package com.example.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class EshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration(){

		// return  a prepared Docket instance
		return new Docket(DocumentationType.SWAGGER_2)
				// to manually configure what to document will go between .select() and .build()
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.eshop"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails()
	{
		return new ApiInfo(
				"Ecommerce API",
				"API for online shopping website ",
				"1.0",
				"Free to use",
				 new springfox.documentation.service.Contact("Dhruvkumar Joshi","#",""),
				"API License",
				"#",
				Collections.emptyList());
	}

}
