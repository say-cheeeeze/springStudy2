package spring;

/**
 * 동일한 이메일을 가진 회원이 이미 존재할 때 처리 클래스
 * @author cheeeeze
 *
 */
public class DuplicateMemberException extends RuntimeException {

	public DuplicateMemberException( String message ) {
		super(message);
	}
	
}
