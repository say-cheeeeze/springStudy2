package spring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberListPrinter {

	
	private MemberDAO memberDAO;
	private MemberPrinter memberPrinter;
	
	
	public MemberListPrinter() {
	}
	
	@Autowired
	public void setMemberDAO( MemberDAO memberDAO ) {
		this.memberDAO = memberDAO;
	}
	@Autowired
	public void setMemberPrinter( MemberPrinter memberPrinter ) {
		this.memberPrinter = memberPrinter;
	}
	
	public void printAll() {
		
		Collection<Member> memberListAll = memberDAO.getMemberAll();
		memberListAll.forEach( item -> memberPrinter.print( item ) );
	}
}
