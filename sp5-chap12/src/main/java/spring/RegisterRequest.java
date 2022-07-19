package spring;

/**
 * 등록하기 위한 Member 객체
 * @author cheeeeze
 *
 */
public class RegisterRequest {
	
	private String email;
	private String password;
	private String confirmPassword;
	private String name;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail( String email ) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword( String password ) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword( String confirmPassword ) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getName() {
		return name;
	}
	public void setName( String name ) {
		this.name = name;
	}
	
	/**
	 * 입력패스워드가 확인패스워드와 일치한지 확인
	 * @return boolean
	 */
	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals( confirmPassword );
	}
	

}
