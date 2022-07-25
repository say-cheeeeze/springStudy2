package spring;

/**
 * 이메일과 비밀번호가 일치하는지 확인하고 AuthInfo 객체를 생성하는 클래스
 * @author cheeeeze
 *
 */
public class AuthService {

	private MemberDAO memberDAO;
	
	public void setMemberDAO( MemberDAO memberDAO ) {
		this.memberDAO = memberDAO;
	}
	
	public AuthInfo authenticate( String email, String password ) {
		
		Member member = memberDAO.selectByEmail( email );
		
		if ( member == null ) {
			throw new WrongIdPasswordException();
		}
		if ( !member.matchPassword( password ) ) {
			throw new WrongIdPasswordException();
		}
		
		return new AuthInfo( 
				member.getMemberId(), 
				member.getMemberEmail(), 
				member.getMemberName() 
				);
	}
}
