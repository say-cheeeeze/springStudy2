package spring;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 이메일이 일치하는 회원 정보를 출력해주는 클래스
 * 
 * Autowired 자동주입설정은 메서드에도 가능하다.
 * 
 * @author cheeeeze
 *
 */
public class MemberInfoPrinter {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private MemberPrinter printer;

	
	public void printMemberInfo( String email ) {
		
		Member member = memberDAO.selectByEmail( email );
		
		if ( member == null ) {
			System.out.println( "일치하는 회원이 없습니다." );
			return;
		}
		
		printer.print( member );
	}
	
	// 메서드에 @Autowired 를 붙이면 AppCtx 클래스에서 MemberInfoPrinter 를 빈 설정하는 부분에
	// setter 메서드가 필요없다.
	@Autowired
	public void setMemberDAO( MemberDAO memberDAO ) {
		this.memberDAO = memberDAO;
	}
	
	@Autowired
	public void setMemberPrinter( MemberPrinter memberPrinter ) {
		this.printer = memberPrinter;
	}
}
