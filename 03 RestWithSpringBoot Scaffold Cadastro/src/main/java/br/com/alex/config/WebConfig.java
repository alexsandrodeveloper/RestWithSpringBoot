package br.com.alex.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.alex.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private static final MediaType MEDIA_TYPE = MediaType.valueOf("application/x-yaml");

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
	}

	public void addCorsMeppings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods("GET","POST","PUT","PATCH","HEAD","DELETE","OPTIONS","TRACE","CONNECT");
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// Escolhe o tipo de aplicação via extensão (.json/.xml)
		/*
		 * configurer.favorParameter(false).ignoreAcceptHeader(false).defaultContentType
		 * (MediaType.APPLICATION_JSON) .mediaType("json",
		 * MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML);
		 */

		// Escolhe o tipo de aplicação query param (mediaType=json/xml)
		/*
		 * configurer.favorPathExtension(false).favorParameter(true).ignoreAcceptHeader(
		 * true) .useRegisteredExtensionsOnly(false).defaultContentType(MediaType.
		 * APPLICATION_JSON) .mediaType("json",
		 * MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML);
		 */

		// Content Negotiation via HEADER Parameter
		configurer.favorPathExtension(false).favorParameter(false).ignoreAcceptHeader(false)
				.useRegisteredExtensionsOnly(false).defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("json", MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML)
				.mediaType("x-yaml", MEDIA_TYPE);
	}
}
