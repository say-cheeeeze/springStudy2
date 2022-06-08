package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import spring.MemberDAO;
import spring.MemberPrinter;

/**
 * excludeFilters 속성을 사용하면 스캔할 때 특정 대상을 자동 등록 대상에서 제외할 수 있다.
 * 정규표현식을 사용해서 제외대상을 지정했다.
 * spring. 으로 시작하고, DAO 로 끝나는 정규표현식을 지정했으므로
 * spring.MemberDAO 클래스를 컴포넌트 스캔 대상에서 제외하게 된다.
 * 
 * FilterType.ASPECTJ 를 필터 타입으로 설정할 수도 있다.
 * 이 타입을 사용하면 정규표현식 대신 AspectJ 패턴을 사용해서 대상을 지정한다.
 * excludeFilters = @Filter(type=FilterType.ASPECTJ, pattern="spring.*DAO" ) )
 * => 이렇게 하면 spring 패키지에서 이름이 DAO 로 끝나는 타입을 컴포넌트 스캔 대상에서 제외한다.
 * 
 * @author cheeeeze
 *
 */
@Configuration
@ComponentScan( basePackages = { "spring" }, 
excludeFilters = @Filter(type=FilterType.REGEX, pattern="spring\\..*DAO" ) )
public class AppCtxWithExclude {

	@Bean
	public MemberDAO memberDAO() {
		return new MemberDAO();
	}
	
	@Bean
	@Qualifier( "printer" )
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
}
