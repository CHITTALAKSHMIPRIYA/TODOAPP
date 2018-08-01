
package com.bridgelabz.todoapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>Configuration class for choosing different property files</b>
 *        </p>
 */
@Configuration
public class EnvConfig {
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
      Resource resource;
      String activeProfile;
      
      PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer =  new PropertySourcesPlaceholderConfigurer();
      
      // get active profile
      activeProfile = System.getProperty("spring.profiles.active");
    
   
      // choose different property files for different active profile
      if ("development".equals(activeProfile)) {
        resource = new ClassPathResource("/META_INF/application_development.properties");
        System.out.println(activeProfile+" profile selected");
     
      } else {
        resource = new ClassPathResource("/META_INF/application_production.properties");
        System.out.println(activeProfile+" profile selected");
      }
      
      // load the property file
      propertySourcesPlaceholderConfigurer.setLocation(resource);
      
      return propertySourcesPlaceholderConfigurer;
    }
  }

