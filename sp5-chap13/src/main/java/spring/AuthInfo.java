package spring;

/**
 * 로그인과 관련된 인증상태 정보 객체
 * @author cheeeeze
 *
 */
public class AuthInfo {

	private Long id;
	private String email;
	private String name;
	
	public AuthInfo( Long id, String email, String name ) {
		this.id = id;
		this.email = email;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
}
