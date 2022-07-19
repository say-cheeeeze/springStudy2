package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.RegistAPIController;
import spring.MemberRegistService;

@Configuration
public class ControllerConfig {
	
	@Autowired
	private MemberRegistService memberRegistService;
	
	@Bean
	public RegistAPIController registAPIController() {
		
		RegistAPIController controller = new RegistAPIController();
		controller.setMemberRegistService( memberRegistService );
		return controller;
	}

}
