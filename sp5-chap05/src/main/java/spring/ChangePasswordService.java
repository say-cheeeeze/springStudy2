package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 비밀번호 변경 서비스 클래스
 * @author cheeeeze
 *
 */
@Component
public class ChangePasswordService {

	@Autowired
	private MemberDAO memberDAO;
	
	// 현재 이 Service 객체의 MemberDAO 필드를 Autowired 로 자동주입시켰기 때문에
	// 이렇게 MemberDAO 객체의 의존주입을 하지 않아도 된다.
	
//	public void setMemberDAO( MemberDAO memberDAO ) {
//		this.memberDAO = memberDAO;
//	}
	
	public void changePassword( String email, String oldPwd, String newPwd ) {
		
		Member member = memberDAO.selectByEmail( email );
		
		if ( member == null ) {
			throw new MemberNotFoundException();
		}
		
		member.changePassword( oldPwd, newPwd );
		
		// changePassword 실패하면 throw 가 되기 때문에 update 를 자연스레 할 수 없게 됨. 
		memberDAO.update( member );
		
	}
	
}
