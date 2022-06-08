//package config;
//
//import java.util.Map;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import spring.ChangePasswordService;
//import spring.MemberDAO;
//import spring.MemberInfoPrinter;
//import spring.MemberListPrinter;
//import spring.MemberPrinter;
//import spring.MemberRegistService;
//import spring.VersionPrinter;
//
///**
// * Assembler 대신 스프링을 사용하여 의존 객체를 주입하는 클래스.
// * 스프링이 어떤 객체를 생성하고, 의존을 어떻게 주입하는지 정의하는 설정정보 클래스
// *
// * AppConfig1, AppConfig2 로 나누어지게 되면서 해당 클래스 전부 주석 처리
// * @author cheeeeze
// *
// */
//@Configuration
//public class AppCtx {
//
//	@Bean
//	public MemberDAO memberDAO() {
//		return new MemberDAO();
//	}
//	
//	@Bean
//	public MemberRegistService memberRegistServie() {
//		return new MemberRegistService( memberDAO() );
//	}
//	@Bean
//	public ChangePasswordService changePwdService() {
//		ChangePasswordService changepwdService = new ChangePasswordService();
//		changepwdService.setMemberDAO( memberDAO() );
//		return changepwdService;
//	}
//	
//	@Bean
//	public MemberPrinter memberPrinter() {
//		return new MemberPrinter();
//	}
//	
//	@Bean
//	public MemberListPrinter listPrinter() {
//		return new MemberListPrinter( memberDAO(), memberPrinter() );
//	}
//	
//	@Bean
//	public MemberInfoPrinter infoPrinter() {
//		
//		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
//		infoPrinter.setMemberDAO( memberDAO() );
//		infoPrinter.setMemberPrinter( memberPrinter() );
//		return infoPrinter;
//	}
//	
//	@Bean
//	public VersionPrinter versionPrinter() {
//		VersionPrinter versionPrinter = new VersionPrinter();
//		versionPrinter.setMajorVersion(5);
//		versionPrinter.setMinorVersion(0);
//		return versionPrinter;
//	}
//	
//	
//}
