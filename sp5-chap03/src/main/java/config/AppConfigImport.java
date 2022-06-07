package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import spring.MemberDAO;
import spring.MemberPrinter;

/**
 * Import 어노테이션을 통해서 함께 사용할 설정 클래스를 지정할 수 있다.
 * 그러면 그 클래스도 함께 사용하기 때문에 스프링 컨테이너를 생성할 때 나머지 설정 클래스를 지정할 필요가 없다.
 * 
 * 또는 Import 대상을 배열로 해서 두 개 이상의 클래스도 Import 할 수 있다.
 *  이런 식으로 @Import( { AppConfig1.class, AppConfig2.class } )
 * 
 * 
 * @author cheeeeze
 *
 */
@Configuration
@Import( AppConfig2.class )
public class AppConfigImport {
	
	// 아래 두 Bean 선언은 AppConfig1.class 와 동일한 내용
	@Bean
	public MemberDAO memberDAO () {
		return new MemberDAO();
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
}
