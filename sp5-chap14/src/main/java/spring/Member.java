package spring;

import java.time.LocalDateTime;

import org.springframework.transaction.annotation.Transactional;

/**
 *	Member객체
 * @author cheeeeze
 *
 */
public class Member {

	private Long memberId;
	private String memberEmail;
	private String memberPassword;
	private String memberName;
	private LocalDateTime inputDateTime;
	
	public Member( String memberEmail, String memberPassword, String memberName, LocalDateTime inputDateTime ) {
		
		this.memberEmail = memberEmail;
		this.memberPassword = memberPassword;
		this.memberName = memberName;
		this.inputDateTime = inputDateTime;
		
	}
	
	public void setMemberId( Long memberId ) {
		this.memberId = memberId;
	}
	
	public Long getMemberId() {
		return this.memberId;
	}
	
	public String getMemberEmail() {
		return this.memberEmail;
	}
	
	public String getMemberPassword() {
		return this.memberPassword;
	}
	
	public String getMemberName() {
		return this.memberName;
	}
	
	public LocalDateTime getInputDateTime() {
		return this.inputDateTime;
	}
	
	@Transactional
	public void changePassword( String oldpwd, String newPwd ) {
		if ( !memberPassword.equals( oldpwd )) {
			throw new WrongIdPasswordException();
		}
		this.memberPassword = newPwd;
	}
	
	public void memberPrint() {
		System.out.println( "ID : " + this.memberId + " / NAME : " + this.memberName + " / EMAIL : " + this.memberEmail );
	}
	
	public boolean matchPassword( String password ) {
		return this.memberPassword.equals( password );
	}
	
	
}
