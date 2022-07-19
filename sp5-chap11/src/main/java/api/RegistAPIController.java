package api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.DuplicateMemberException;
import spring.MemberRegistService;
import spring.RegisterRequest;

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
	public String handleStep2( @RequestParam( value = "agree", defaultValue = "false" ) Boolean agree, Model model ) {
		if ( !agree ) {
			return goStep1();
		}
		
		model.addAttribute( "registerRequest", new RegisterRequest() );
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
	 * @param req
	 * @return
	 */
	@PostMapping( "/step3" )
//	public String handleStep3( RegisterRequest req ) {
	public String handleStep3( @ModelAttribute( "formData" ) RegisterRequest req ) {
		
		
		try {
			
			memberRegistService.regist( req );
			return goStep3();
		}
		catch( DuplicateMemberException e ) {
			return goStep2();
		}
	}
	
	
	
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
