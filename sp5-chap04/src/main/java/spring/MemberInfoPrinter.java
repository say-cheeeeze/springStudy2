package spring;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 이메일이 일치하는 회원 정보를 출력해주는 클래스
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
	
	public void setMemberDAO( MemberDAO memberDAO ) {
		this.memberDAO = memberDAO;
	}
	
	public void setMemberPrinter( MemberPrinter memberPrinter ) {
		this.printer = memberPrinter;
	}
}
