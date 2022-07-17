package spring;

import org.springframework.transaction.annotation.Transactional;

/**
 * 비밀번호 변경 서비스 클래스
 * @author cheeeeze
 *
 */

public class ChangePasswordService {

	private MemberDAO memberDAO;
	
	public void setMemberDAO( MemberDAO memberDAO ) {
		this.memberDAO = memberDAO;
	}
	
	@Transactional
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
