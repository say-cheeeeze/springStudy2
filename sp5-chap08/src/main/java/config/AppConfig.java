package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import spring.ChangePasswordService;
import spring.MemberDAO;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegistService;

@Configuration
@EnableTransactionManagement
public class AppConfig {
	
	private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/spring5fs?enabledTLSProtocols=TLSv1.2&characterEncoding=utf8";
	private static final String USER_NAME = "spring5";
	private static final String USER_PASSWORD = "spring5";
	
	@Bean( destroyMethod = "close" )
	public DataSource dataSource() {
		
		DataSource ds = new DataSource();
		ds.setDriverClassName( DRIVER_CLASS_NAME );
		ds.setUrl( JDBC_URL );
		ds.setUsername( USER_NAME );
		ds.setPassword( USER_PASSWORD );
		ds.setInitialSize( 2 );	// 커넥션 풀이 초기화될 때 생성할 초기 커넥션 갯수
		ds.setMaxActive( 10 );	// 커넥션 풀에서 가져올 수 있는 활성 가능한 커넥션 최대 갯수
		ds.setMaxIdle( 10 );
		ds.setTestWhileIdle( true ); // 커넥션 풀에 유휴 상태로 있는 동안 검사할지 여부
		
		// 커넥션 풀에 유휴 상태로 유지할 최소 밀리세컨. 이 시간을 초과하면 pool 에서 커넥션을 제거하게 된다.
		ds.setMinEvictableIdleTimeMillis( 1000 * 60 * 3 ); // 3분
		ds.setTimeBetweenEvictionRunsMillis( 1000 * 10 ); // 10초 주기로 커넥션 풀의 유휴 커넥션을 검사
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource( dataSource() );
		return tm;
	}
	
	@Bean
	public MemberRegistService memberRegistService() {
		return new MemberRegistService( memberDAO() );
	}

	@Bean
	public MemberDAO memberDAO() {
		return new MemberDAO( dataSource() );
	}
	
	@Bean
	public ChangePasswordService changePasswordService() {
		ChangePasswordService changePasswordService = new ChangePasswordService();
		changePasswordService.setMemberDAO( memberDAO() );
		return changePasswordService;
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberListPrinter memberListPrinter() {
		return new MemberListPrinter( memberDAO(), memberPrinter());
	}
	
	@Bean
	public MemberInfoPrinter memberInfoPrinter() {
		MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
		memberInfoPrinter.setMemberDAO( memberDAO() );
		memberInfoPrinter.setMemberPrinter( memberPrinter() );
		return memberInfoPrinter;
	}
}
