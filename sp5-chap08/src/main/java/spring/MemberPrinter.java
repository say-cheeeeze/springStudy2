package spring;

public class MemberPrinter {

	
	public void print( Member member ) {
		
		System.out.println( "==== 회원 정보 ====" );
		System.out.printf(
				"회원 정보 : 아이디 = %d, 이메일 = %s, 이름 = %s, 등록일 = %tF\n",
				member.getId(), member.getMemberEmail(), member.getMemberName(), member.getInputDateTime());
		
	}
}
