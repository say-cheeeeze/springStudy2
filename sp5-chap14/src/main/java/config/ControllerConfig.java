package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.ChangePwdController;
import api.LoginController;
import api.LogoutController;
import api.MainController;
import api.MemberSearchController;
import api.RegistAPIController;
import api.SurveyAPIController;
import spring.AuthService;
import spring.ChangePasswordService;
import spring.MemberDAO;
import spring.MemberRegistService;

@Configuration
public class ControllerConfig {
	
	@Autowired
	private MemberRegistService memberRegistService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private ChangePasswordService changePasswordService;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Bean
	public RegistAPIController registAPIController() {
		
		RegistAPIController controller = new RegistAPIController();
		controller.setMemberRegistService( memberRegistService );
		return controller;
	}
	
	@Bean
	public SurveyAPIController surveyController() {
		return new SurveyAPIController();
	}
	
	@Bean
	public LoginController loginController() {
		LoginController loginController = new LoginController();
		loginController.setAuthService( authService );
		return loginController;
	}

	@Bean
	public LogoutController logoutController() {
		return new LogoutController();
	}
	
	@Bean
	public MainController mainController() {
		return new MainController();
	}
	
	@Bean
	public ChangePwdController changePwdController() {
		ChangePwdController changePwdController =  new ChangePwdController();
		changePwdController.setChangePasswordService( changePasswordService );
		return changePwdController;
	}
	
	@Bean
	public MemberSearchController memberSearchController() {
		MemberSearchController memberSearchController = new MemberSearchController();
		memberSearchController.setMemberDAO( memberDAO );
		return memberSearchController;
	}
}
