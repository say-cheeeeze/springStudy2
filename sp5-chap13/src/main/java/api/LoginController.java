package api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.AuthInfo;
import spring.AuthService;
import spring.WrongIdPasswordException;

@Controller
@RequestMapping( "/login")
public class LoginController {

	private AuthService authService;
	
	public void setAuthService( AuthService authService ) {
		this.authService = authService;
	}
	
	@GetMapping
	public String form( LoginCommand loginCommand ) {
		return toLoginForm();
	}
	
	@PostMapping
	public String submit( LoginCommand loginCommand, Errors errors, HttpSession session ) {
		
		new LoginCommandValidator().validate( loginCommand, errors);
		
		if ( errors.hasErrors() ) {
			return toLoginForm();
		}
		
		try {
			AuthInfo authInfo = authService.authenticate( loginCommand.getEmail(), loginCommand.getPassword() );

			// 세션에 authInfo 를 저장
			//	
			session.setAttribute( "authInfo", authInfo );
			
			return toLoginSuccess();
		}
		catch( WrongIdPasswordException e ) {
			errors.reject( "idPasswordNotMatching" );
			return toLoginForm();
		}
		
		
	}
	
	private String toLoginForm() {
		return "login/loginForm";
	}
	
	private String toLoginSuccess() {
		return "login/loginSuccess";
	}
}
