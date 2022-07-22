package api;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
	
	@RequestMapping( "/logout" )
	public String logout( HttpSession session ) {
		
		// 현재 세션에 할당된 모든 객체를 해제한다.
		session.invalidate();
		
		// "redirect:/~" 는 웹애플리케이션 기준으로 이동 경로를 설정한다.
		return "redirect:/main";
	}
	

}
