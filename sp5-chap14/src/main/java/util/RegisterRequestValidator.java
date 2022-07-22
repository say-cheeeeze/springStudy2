package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.RegisterRequest;

/**
 * 회원 정보 등록 시 사용할 Validator 객체
 * 커맨드 객체에서 유효성 검증하기 위해 Validator 인터페이스를 상속한 클래스.
 * 
 * @author cheeeeze
 *
 */
public class RegisterRequestValidator implements Validator {

	private static final String emailRegExp = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
			"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private Pattern pattern;
	
	public RegisterRequestValidator() {
		pattern = Pattern.compile( emailRegExp );
	}
	
	/**
	 * supports 메서드는 파라미터로 전달받은 clazz 객체가 RegisterRequest 클래스로 타입 변환이 가능한지 확인한다.
	 * ( 이 예제에서는 supports() 메서드를 실행하지는 않지만 SpringMVC 가 자동 검증 기능을 수행하도록 하려면
	 * supports() 메서드를 올바르게 구현해야 한다.
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterRequest.class.isAssignableFrom(clazz);
	}
	
	/**
	 * validate() 메서드는 Object 와 Errors 파라미터가 필요한데,
	 * Object : 검사할 대상 객체이고,
	 * Errors : 검사결과에서 에러 코드를 설정하기 위한 객체
	 * 
	 * validate() 메서드는 검사 대상의 특정 프로퍼티나 상태가 올바른지 검사한다.
	 * 또 올바르지 않다면 Errors 의 rejectValue() 메서드를 이용하여 에러 코드를 지정한다.
	 * 
	 */
	@Override
	public void validate( Object target, Errors errors ) {
		
		RegisterRequest req = ( RegisterRequest )target;
		
		// email 이 없거나 빈 문자열인 경우
		if ( req.getEmail() == null || req.getEmail().trim().isEmpty() ) {
			
			// email 이라는 프로퍼티의 에러코드로 required 를 추가한다.
			errors.rejectValue( "email", "required" );
		}
		else {
			
			Matcher matcher = pattern.matcher( req.getEmail() );
			
			if ( !matcher.matches() ) {
				errors.rejectValue( "email", "bad" );
			}
		}
		
		// errors 객체의 getFieldValue("name") 을 사용하여 객체 값을 구하기 때문에
		// 검사 대상 객체를 파라미터로 전달하지 않아도 값 검증을 할 수 있다.
		// 검사 대상 객체의 name 프로퍼티 값이 null 이거나 공백인 경우 name 프로퍼티의 에러코드로 required 를 추가한다.
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "name", "required" );
		
		
		ValidationUtils.rejectIfEmpty( errors, "password", "required" );
		ValidationUtils.rejectIfEmpty( errors, "confirmPassword", "required" );
		
		if ( !req.getPassword().isEmpty() ) {
			if ( !req.isPasswordEqualToConfirmPassword() ) {
				errors.rejectValue( "confirmPassword", "nomatch" );
			}
		}
		
	}
	
	
	
	
}
