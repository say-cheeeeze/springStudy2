package api;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ChangePwdCommandValidator implements Validator {

	/**
	 * supports 메서드는 파라미터로 전달받은 clazz 객체가 비교 인자로 주어지는 객체 타입으로 변환이 가능한지 확인한다.
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return ChangePwdCommand.class.isAssignableFrom( clazz );
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
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "currentPassword", "required" );
		ValidationUtils.rejectIfEmpty( errors, "newPassword", "required" );
	}
}
