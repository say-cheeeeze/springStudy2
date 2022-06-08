package spring;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class MemberPrinter {

	private DateTimeFormatter dateTimeFormatter;
	
	public MemberPrinter() {
		dateTimeFormatter = DateTimeFormatter.ofPattern( "yyyy년 MM월 dd일" );
	}
	
	public void print( Member member ) {
		
		System.out.println( "==== 회원 정보 ====" );
		
		if ( dateTimeFormatter == null ) {
			System.out.printf(
					"회원 정보 : 아이디 = %d, 이메일 = %s, 이름 = %s, 등록일 = %tF\n",
					member.getId(), member.getMemberEmail(), member.getMemberName(), member.getInputDateTime());
		}
		else {
			System.out.printf(
					"회원 정보 : 아이디 = %d, 이메일 = %s, 이름 = %s, 등록일 = %s\n",
					member.getId(), member.getMemberEmail(), member.getMemberName(), 
					dateTimeFormatter.format( member.getInputDateTime() ) );
			
		}
		
		
	}
	
	@Autowired
	public void setDateFormatter( @Nullable DateTimeFormatter dateTimeFormatter ) {
		this.dateTimeFormatter = dateTimeFormatter;
		
	}
}
