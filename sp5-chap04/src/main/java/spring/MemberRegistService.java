package spring;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *  회원가입 서비스 레파지토리 인터페이스 객체
 * @author cheeeeze
 *
 */
public class MemberRegistService {

	@Autowired
	private MemberDAO memberDAO;
	
	/**
	 * 회원가입 서비스.  의존 객체 전달
	 * @param memberDAO
	 */
	public MemberRegistService() {
	}
	
	public MemberRegistService( MemberDAO memberDAO ) {
		this.memberDAO = memberDAO;
	}
	
	/**
	 * 신규 회원 가입을 시키고, ID 를 반환함.
	 * @param request
	 * @return long
	 */
	public Long regist( RegisterRequest request ) {
		
		Member member = memberDAO.selectByEmail( request.getEmail() );
		
		if ( member != null ) {
			throw new DuplicateMemberException( "email 중복!" + request.getEmail() );
		}
		
		Member newMember = new Member(
				request.getEmail(), request.getPassword(), request.getName(), LocalDateTime.now() );
		
		memberDAO.insert( newMember );
		
		return newMember.getId();
		
		
	}
	
}
