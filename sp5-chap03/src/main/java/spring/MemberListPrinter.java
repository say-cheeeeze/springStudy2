package spring;

import java.util.Collection;

public class MemberListPrinter {

	
	private MemberDAO memberDAO;
	private MemberPrinter memberPrinter;
	
	public MemberListPrinter( MemberDAO memberDAO, MemberPrinter memberPrinter) {
		this.memberDAO = memberDAO;
		this.memberPrinter = memberPrinter;
	}
	
	public void printAll() {
		
		Collection<Member> memberListAll = memberDAO.getMemberAll();
		memberListAll.forEach( item -> memberPrinter.print( item ) );
	}
}
