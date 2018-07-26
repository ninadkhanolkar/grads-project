package com.systems.wissen.config;

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
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
	    String title="WiseConnect Rest API";
		String description="";
		String version="V1";
		String termsOfServiceUrl="For Use of Wissen employees";
		Contact contact=new Contact("WiseConnect", "www.wissen.com", "wise-connect@wissen.com");
		String license="Apache Commons";
		String licenseUrl="API license URL";
		return new ApiInfo(title, description, version, termsOfServiceUrl, contact, license, licenseUrl, Collections.emptyList());
	}
}