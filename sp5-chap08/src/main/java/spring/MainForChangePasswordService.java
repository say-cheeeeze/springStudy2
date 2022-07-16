package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConfig;

/**
 * 트랜잭션 처리를 위한 main 메서드.
 * 스프링은 트랜잭션 처리를 위해 프록시 객체를 이용한다
 * @author cheeeeze
 *
 */
public class MainForChangePasswordService {
	
	public static void main( String[] args ) { 
		
		AnnotationConfigApplicationContext appCtx = new AnnotationConfigApplicationContext( AppConfig.class );
		
		// @Transactional 애노테이션이 붙어있는 메서드가 있다.
		// getBean 하면 스프링은 트랜잭션 처리를 하기 위해 ChangePasswordService 객체를 반환하는 대신
		// 프록시 객체를 반환한다!
		ChangePasswordService cpService = appCtx.getBean( "changePasswordService", ChangePasswordService.class );
		
		try {
			
			cpService.changePassword("nyj9349@hanmail.net", "1234", "12345");
			System.out.println( "패스워드를 변경했습니다." );
			
		}
		catch( MemberNotFoundException e ) {
			System.out.println( "회원이 존재하지 않습니다." );
		}
		catch( WrongIdPasswordException e ) {
			System.out.println( "비밀번호가 올바르지 않습니다." );
		}
		
		appCtx.close();
		
	}

}
