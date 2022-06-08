package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDAO;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegistService;
import spring.VersionPrinter;

/**
 * Assembler 대신 스프링을 사용하여 의존 객체를 주입하는 클래스.
 * 스프링이 어떤 객체를 생성하고, 의존을 어떻게 주입하는지 정의하는 설정정보 클래스
 * 
 * @Component 애너테이션을 붙인 클래스를 스캔해서 찾는다. 
 * =>> basePackages 에 선언한 패키지와 그 하위 패키지에 속한 클래스를 대상으로 스캔한다.
 * =>> @Component 애노테이션이 붙은 클래스의 객체를 생성해서 Bean 으로 등록한다.
 *
 * @author cheeeeze
 *
 */
@Configuration
@ComponentScan( basePackages = { "spring" } )
public class AppCtx {

	
	@Bean
	public MemberRegistService memberRegistServie() {
		return new MemberRegistService();
	}
	@Bean
	public ChangePasswordService changePwdService() {
		return new ChangePasswordService();
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
	
	
}
