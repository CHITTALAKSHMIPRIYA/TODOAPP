package com.bridgelabz.todoapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;

import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	/**
	 * To enable the Swagger 2 we use the annotation @EnableSwagger2. A Docket bean
	 * is defined and using its select() method we get an instance of
	 * ApiSelectorBuilder. ApiSelectorBuilder we configure the endpoints exposed by
	 * Swagger. After the Docket bean is defined, its select() method returns an
	 * instance of ApiSelectorBuilder, which provides a way to control the endpoints
	 * exposed by Swagger.
	 * 
	 * @return
	 */
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("token").apiInfo(apiInfo()).select()
				.paths(postPaths()).build();
	}

	/**
	 * Using the RequestHandlerSelectors and PathSelectors we configure the
	 * predicates for selection of RequestHandlers.
	 * 
	 * @return
	 */
	private Predicate<String> postPaths() {
		return or(regex("/user/.*"), regex("/note/.*"));
	}

	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("TODO APPLICATION")
				.description("TODO APPLICATION USING SPRING BOOT AND MONGODB").termsOfServiceUrl("http://javainuse.com")
				.contact("lakshmichitta96@gmail.com").version("1.0").build();
	}
	@Bean
	SecurityConfiguration config()
	{
		return new SecurityConfiguration(null,null,null,null,"token",ApiKeyVehicle.HEADER,"token",",");
		
	
		
	}
}
