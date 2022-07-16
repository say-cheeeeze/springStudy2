package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap09.HelloController;

@Configuration
public class BeanConfig {
	
	@Bean
	public HelloController helloController() {
		return new HelloController();
	}

}
