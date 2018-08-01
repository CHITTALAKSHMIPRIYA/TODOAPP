//
//package com.bridgelabz.todoapp.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import com.bridgelabz.todoapp.userservice.usermodel.User;
//
///**
// * @author LAKSHMI PRIYA
// * @since DATE:10-07-2018
// *        <p>
// *        <b>A POJO class with the user details.</b>
// *        </p>
// */
//@Configuration
//@Component
//public class RedisConfig {
//	
//	
//	
//	/**
//	 * @return jedisConFactory
//	 */
//	@SuppressWarnings("deprecation")
//	@Bean
//	JedisConnectionFactory jedisConnectionFactory() {
//		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
//		//jedisConFactory.setHostName("localhost");
//		//jedisConFactory.setPort(6379);
//		return jedisConFactory;
//	}
//
//	/**
//	 * @return redis template
//	 */
//	@Bean
//	public RedisTemplate<String, User> redisTemplate() {
//		RedisTemplate<String, User> redisTemplate = new RedisTemplate<String, User>();
//		redisTemplate.setConnectionFactory(jedisConnectionFactory());
//		return redisTemplate;
//	}
//}
