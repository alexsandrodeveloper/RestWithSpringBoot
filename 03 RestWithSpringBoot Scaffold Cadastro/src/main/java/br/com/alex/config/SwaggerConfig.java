package br.com.alex.config;

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
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("br.com.alex"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	@SuppressWarnings("unchecked")
	private ApiInfo apiInfo() {

		return new ApiInfo("RestFull API whit Spring Boot 2.1.3", "Descrição sobre a API", "V1", "terms Of Service Url",
				new Contact("Alex Sandro Soares", "www.alexsandro.com.br", "alex_javaman.com"), "Licença da API",
				"Licença da URL", Collections.EMPTY_LIST);
	}
}
