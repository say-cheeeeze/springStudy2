package assembler;

import spring.ChangePasswordService;
import spring.MemberDAO;
import spring.MemberRegistService;

/**
 * 애플리케이션에서 필요한 클래스를 스프링에 잘 조립되도록 설정하는 부분
 * 의존 주입의 대상이 될 객체를 생성한다. 
 * @author cheeeeze
 *
 */
public class Assembler {

	private MemberDAO memberDAO;
	private MemberRegistService memberRegService;
	private ChangePasswordService changePwdService;
	
	public Assembler() {
		memberDAO = new MemberDAO();
		memberRegService = new MemberRegistService( memberDAO );
		changePwdService = new ChangePasswordService();
		changePwdService.setMemberDAO( memberDAO );
	}
	
	public MemberDAO getMemberDAO() {
		return memberDAO;
	}
	
	public MemberRegistService getMemberRegistService() {
		return memberRegService;
	}
	
	public ChangePasswordService getChangePasswordService() {
		return changePwdService;
	}
	
	
}
