package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.MemberDAO;
import spring.MemberPrinter;

@Configuration
public class AppConfig1 {
	
	@Bean
	public MemberDAO memberDAO () {
		return new MemberDAO();
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}

}
