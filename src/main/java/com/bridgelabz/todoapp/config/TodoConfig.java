
package com.bridgelabz.todoapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>Config class for ModelMapper.</b>
 *        </p>
 */
@Configuration
public class TodoConfig {
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
