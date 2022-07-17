package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
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

	/**
	 * DispatcherServlet 의 매핑 경로를 '/' 로 주었을 때, jsp/html/css 등을 올바르게 처리하기 위한 설정.
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	
	/**
	 * JSP 를 이용해서 컨트롤러의 실행 결과를 매핑하기 위한 설정
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/", ".jsp");
	}

}
