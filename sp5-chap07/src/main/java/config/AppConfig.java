package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.RecCalculator;

/**
 * 
 * MainAspect.java 에서 설정에 쓰일 설정 클래스 
 * 
 * Aspect 애노테이션을 붙인 클래스를 공통 기능으로 적용하려면 
 * EnableAspectJAutoProxy 애노테이션을 설정 클래스에 붙여야한다.
 * 
 * 이 애노테이션을 추가하면 스프링은 @Aspect 애노테이션이 붙은 Bean 객체를 찾아서
 * Bean 객체의 @Pointcut 설정과 @Around 설정을 사용한다.
 * 
 * 
 * @author cheeeeze
 *
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = false) // 인터페이스를 상속받아 프록시를 생성하는게 아니라 클래스를 통해 프록시를 생성하려면 true 를 준다.
public class AppConfig {
	
	@Bean
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}
	
	@Bean
	public Calculator calculator() {
		return new RecCalculator();
	}

}
