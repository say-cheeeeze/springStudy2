package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDAO;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegistService;
import spring.VersionPrinter;

/**
 * 이 설정 클래스에서는 Autowired 를 사용하여 스프링 컨테이너에 빈 주입
 * @return
 */
@Configuration
public class AppConfig2 {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private MemberPrinter memberPrinter;

	@Bean
	public MemberRegistService memberRegService() {
		return new MemberRegistService( memberDAO );
	}
	
	@Bean
	public ChangePasswordService changePwdService() {
		ChangePasswordService changePwdService = new ChangePasswordService();
		changePwdService.setMemberDAO( memberDAO );
		return changePwdService;
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		MemberListPrinter listPrinter = new MemberListPrinter( memberDAO, memberPrinter );
		return listPrinter;
	}
	
	@Bean
	public MemberInfoPrinter memberInfoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();

		// MemberInfoPrinter 클래스에서 두 객체를 Autowired 로 주입했기 때문에 여기서 setter 가 필요없어짐. 
//		infoPrinter.setMemberDAO( memberDAO );
//		infoPrinter.setMemberPrinter( memberPrinter );
		return infoPrinter;
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
