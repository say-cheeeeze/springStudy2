package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import interceptor.AuthCheckInterceptor;
import util.RegisterRequestValidator;

/**
 * 스프링 MVC 설정 클래스
 * 
 * @EnableWebMvc 애노테이션은 스프링 MVC 설정을 활성화한다.
 * 스프링 MVC 를 사용하는데 다양한 설정을 생성한다.
 * 
 * 
 * @author cheeeeze
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp( "/WEB-INF/view/", ".jsp" );
	}

	/**
	 * main 페이지 요청 경로와 뷰 이름을 연결하기 위해 추가 
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController( "/main" ).setViewName( "main" );
	}
	
	/**
	 * 글로벌 범위 Validator 설정하기 위해 WebMvcConfigurer 의 getValidator() 메서드의 반환타입을
	 * Validator 인터페이스의 구현 객체로 설정
	 * 
	 * -> Bean Validation 을 사용하기 위해 주석처리 ( 스프링은 별도로 설정한 글로벌 Validator 가 없을 경우에
	 * OptionalValidatorFactoryBean 을 글로벌 범위 Validator 로 사용한다. 이 설정이 남아있으면
	 * OptionalValidatorFactoryBean 을 사용할 수 없게 된다. )
	 * https://www.notion.so/chapter-12-Spring-MVC-958e7fe0fd5942219a5aeb619596704a
	 * 
	 */ 
//	@Override
//	public Validator getValidator() {
//		return new RegisterRequestValidator();
//	}
	
	/**
	 * HandleInterceptor 구현체를 적용할 설정을 추가한다.
	 * 
	 * addPathPatterns 는 인터셉트를 적용할 경로 패턴을 Ant 경로 패턴을 이용하여 설정한다.
	 * 제외할 경로 패턴은 excludePathPatterns 로 추가한다. 두 개 이상이면 각 경로 패턴을 콤마로 구분한다.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor( authCheckInterceptor() )
				.addPathPatterns( "/edit/**" )
				.excludePathPatterns( "/edit/help/**" );
	}
	
	@Bean
	public AuthCheckInterceptor authCheckInterceptor() {
		return new AuthCheckInterceptor();
	}
}
