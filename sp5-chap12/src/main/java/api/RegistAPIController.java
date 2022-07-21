package api;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.DuplicateMemberException;
import spring.MemberRegistService;
import spring.RegisterRequest;
import util.RegisterRequestValidator;

@Controller
@RequestMapping( "/register")
public class RegistAPIController {
	
	private MemberRegistService memberRegistService;
	
	public void setMemberRegistService( MemberRegistService memberRegistService ) {
		this.memberRegistService = memberRegistService;
	}
	
	/**
	 * 회원 가입 화면으로 이동
	 * @return
	 */
	@RequestMapping( "/step1" )
	public String handleStep1() {
		return goStep1();
	}

	/**
	 * 회원가입 처리 HttpServletRequest 이용
	 * @param request
	 * @return
	 */
	/**
	@RequestMapping( "/step2" )
	public String handleStep2( HttpServletRequest request ) {
		
		String agree = request.getParameter( "agree" );
		
		System.out.println( "agree : " + agree );
		
		if ( agree == null || !agree.equals( "true" ) ) {
			return goStep1();
		}
		return goStep2();
	}
	*/
	
	
	/**
	 * 회원가입 처리 RequestParam 이용
	 * @param agree
	 * @return
	 */
	@PostMapping( "/step2" )
	public String handleStep2( @RequestParam( value = "agree", defaultValue = "false" ) Boolean agree, RegisterRequest reqigsterRequest ) {
		if ( !agree ) {
			return goStep1();
		}
		
		return goStep2();
	}
	
	/**
	 * step2 get 방식으로 요청한 경우 step1(첫페이지)로 리다이렉트
	 * 
	 * “ / “ 로 시작하면 웹 애플리케이션 기준으로 이동 경로를 생성한다.
	 * “ / ” 로 시작하지 않으면 현재 경로를 기준으로 상대경로를 사용한다.
	 * 
	 * @return
	 */
	@GetMapping( "/step2" )
	public String redirectStep2() {
		
		// 이렇게 하면 http://localhost:8080/sp5-chap11/register/register/step1 로 간다..
		// 잘못된 방식
		// return "redirect:register/step1";
		
		// 이렇게 완전한 URL 도 가능
		// return "redirect:http://localhost:8080/sp5-chap11/register/step1";
		
		// 이렇게 하면 /sp5-chap11/register/step1 로 이동한다. 
		return "redirect:/register/step1";
		
	}
	
	/**
	 * 회원 가입 처리합니다.
	 * 커맨드 객체를 이용하여 파라미터 세팅
	 * 
	 * 회원가입에 성공하면 3 단계로 이동
	 * 동일한 이메일 주소를 가진 회원이 있으면 2 페이지로 다시 이동
	 * 
	 * ModelAttribute 는 View 에서 사용할 커맨드 객체명을 바꿀 수 있게 해줌
	 * 
	 * 주의해야할 점은 메소드 시그니처의 파라미터 순서에
	 * 커맨드 객체보다 Errors 선언 위치가 뒤쪽에 위치해야한다.
	 * 
	 * 커맨드 객체에 @Valid 애노테이션을 통해 글로벌 검증할 수 있다.
	 * 내부 메소드에서 검증할 로직을 작성하지 않아도 된다. 단, Errors 파라미터는 메소드 시그니처에 포함되어야 한다.
	 * 없는 경우에 400 에러가 전달된다. 
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping( "/step3" )
	public String handleStep3( @Valid RegisterRequest req, Errors errors ) {
		
		// RegisterRequest 커맨드 객체의 값이 올바른지 검사하고 그 결과를 Errors 객체에 담는다.
//		new RegisterRequestValidator().validate( req, errors );
		
		// 유효하지 않은 값이 있으면 rejectValue() 메서드를 실행하면 errors.hasErrors() 메서드는 true 를 반환한다.
		// 그 때엔 step2 페이지로 이동하게 한다.
		if ( errors.hasErrors() ) {
			return goStep2();
		}
		
		try {
			
			memberRegistService.regist( req );
			return goStep3();
		}
		catch( DuplicateMemberException e ) {
			// 동일한 이메일을 가진 회원 데이터가 있으면 email 프로퍼티의 에러 코드로 duplicate 를 추가한다.
			errors.rejectValue( "email", "duplicate" );
			return goStep2();
		}
	}
	
	// -> Bean Validation 을 사용하기 위해 주석처리 ( 스프링은 별도로 설정한 글로벌 Validator 가 없을 경우에
	// OptionalValidatorFactoryBean 을 글로벌 범위 Validator 로 사용한다. 이 설정이 남아있으면
	// OptionalValidatorFactoryBean 을 사용할 수 없게 된다. )
	// https://www.notion.so/chapter-12-Spring-MVC-958e7fe0fd5942219a5aeb619596704a
//	@InitBinder
//	protected void initBinder( WebDataBinder binder ) {
//		binder.setValidator( new RegisterRequestValidator() );
//	}
	
	
	
	
	private String goStep1() {
		
		System.out.println( "########################" );
		System.out.println( "회원 가입1 화면으로 이동합니다.." );
		return "register/step1";
	}
	
	private String goStep2() {
		
		System.out.println( "########################" );
		System.out.println( "회원 가입2 화면으로 이동합니다.." );
		return "register/step2";
	}
	
	private String goStep3() {
		
		System.out.println( "########################" );
		System.out.println( "회원 가입3 화면으로 이동합니다.." );
		return "register/step3";
	}
}
