package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
}
