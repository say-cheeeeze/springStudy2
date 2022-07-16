package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConfig;
import spring.Member;
import spring.MemberDAO;

public class MainForMemberDAO {

	private static MemberDAO memberDAO;
	
	public static void main( String[] args ) {
		
		AnnotationConfigApplicationContext appCtx = new AnnotationConfigApplicationContext( AppConfig.class );
		
		memberDAO = appCtx.getBean( MemberDAO.class );
		
		listAll();
	
		updateMember();
		
		insertMember();
		listAll();
		
		appCtx.close();
	}
	
	
	private static void listAll() {
		
		System.out.println( "listAll() ....... " );
		
		int total = memberDAO.memberCount();
		
		System.out.println( "count...." + total );
		
		List<Member> memberList = memberDAO.listAll();
		
		if ( memberList.isEmpty() ) {
			return;
		}
		
		for ( Member member : memberList ) {
			member.memberPrint();
		}
		
	}
	
	private static void updateMember() {
		
		System.out.println( "updateMember()......" );
		
		String email = "nyj9349@hanmail.net";
		
		Member member = memberDAO.selectByEmail( email );
		
		if ( member == null ) {
			System.out.println( email + "에 해당하는 회원이 없습니다." );
			return;
		}
		
		String oldPwd = member.getMemberPassword();
		String newPwd = "1234";
		
		member.changePassword( oldPwd, newPwd );
		
		memberDAO.update( member );
		
		System.out.println( "비밀번호를 변경했읍니다~~" );
		
	}
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "MMddHHmmss" );
	
	private static void insertMember() {
		
		System.out.println( "insertMember()...... " );
		
		String prefix = formatter.format( LocalDateTime.now() );
		
		Member member = new Member( prefix + "@test.com", prefix, prefix, LocalDateTime.now() );
		
		memberDAO.insert( member );
		
		System.out.println( "새 member 추가..... " + member.getId() + " / " + member.getMemberName() + " / " + member.getMemberEmail() );
	}
	
	
	
	
}
