
package com.bridgelabz.todoapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bridgelabz.todoapp.utilservice.TokenParseInterceptor;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>A POJO class with the user details.</b>
 *        </p>
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	TokenParseInterceptor tokenparseinterceptor;

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TokenParseInterceptor()).addPathPatterns("/note/**");

	}
}
