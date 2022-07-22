package api;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.AuthInfo;
import spring.ChangePasswordService;
import spring.WrongIdPasswordException;

/**
 * 비밀번호 변경 컨트롤러
 * 
 * 
 */
@Controller
@RequestMapping( "/edit/changePassword" )
public class ChangePwdController {
	
	private ChangePasswordService changePasswordService;
	
	public void setChangePasswordService( ChangePasswordService changePasswordService ) {
		this.changePasswordService = changePasswordService;
	}
	
	@GetMapping
	public String form( @ModelAttribute( "command" ) ChangePwdCommand pwdCmd, HttpSession session ) {
		
		System.out.println( "#########################" );
		System.out.println( "#### /edit/changePassword 컨트롤러입니다...... ####" );
		System.out.println( "#########################" );
		
		AuthInfo authInfo = ( AuthInfo ) session.getAttribute( "authInfo" );
		
		// 만약 로그인하지 않았으면 로그인화면으로 리다이렉트
		if ( authInfo == null ) {
			return "redirect:/login";
		}
		
		return "edit/changePwdForm";
	}
	
	@PostMapping
	public String submit( @ModelAttribute( "command" ) ChangePwdCommand pwdCmd, 
			Errors errors, HttpSession session ) {
		
		new ChangePwdCommandValidator().validate( pwdCmd, errors);
		
		if ( errors.hasErrors() ) {
			return "edit/changePwdForm";
		}
		
		AuthInfo authInfo = ( AuthInfo ) session.getAttribute( "authInfo" );
		
		try {
			changePasswordService.changePassword(authInfo.getEmail(), pwdCmd.getCurrentPassword(), pwdCmd.getNewPassword() );
			return "edit/changedPwd";
		}
		catch( WrongIdPasswordException e ) {
			errors.rejectValue( "currentPassword", "notMatching" );
			return "edit/changePwdForm";
		}
			
		
	}

}
