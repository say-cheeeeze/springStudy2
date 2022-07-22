package api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
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
	public String form( LoginCommand loginCommand, @CookieValue( value = "REMEMBER", required = false ) Cookie cookie ) {
		
		if ( cookie != null ) {
			loginCommand.setEmail( cookie.getValue() );
			loginCommand.setRememberEmail( true );
		}
		return toLoginForm();
	}
	
	@PostMapping
	public String submit( LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletResponse response ) {
		
		new LoginCommandValidator().validate( loginCommand, errors);
		
		if ( errors.hasErrors() ) {
			return toLoginForm();
		}
		
		try {
			AuthInfo authInfo = authService.authenticate( loginCommand.getEmail(), loginCommand.getPassword() );

			// 세션에 authInfo 를 저장
			//	
			session.setAttribute( "authInfo", authInfo );
			
			Cookie rememberEmailCookie = new Cookie( "REMEMBER", loginCommand.getEmail() );
			rememberEmailCookie.setPath( "/" );
			
			// 기억하기를 했다면 쿠키를 30일동안 유지되도록 초단위 설정한다. 60 * 60 * 24 * 30
			if ( loginCommand.isRememberEmail() ) {
				rememberEmailCookie.setMaxAge( 60 * 60 * 24 * 30 );
			}
			else {
				rememberEmailCookie.setMaxAge(0);
			}
			
			response.addCookie( rememberEmailCookie );
			
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
