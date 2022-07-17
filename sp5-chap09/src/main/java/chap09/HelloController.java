package chap09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 컨트롤러는 웹 요청을 처리하고 그 결과를 뷰에 전달하는 스프링 Bean 객체이다.
 * @author cheeeeze
 *
 */
@Controller
public class HelloController {

	@GetMapping( "/hello" )
	public String hello( Model model, @RequestParam( value = "name", required = false ) String name ) {
	
		// greeting 이라는 모델 속성에 값을 부여한다.( 파라미터1 = 속성명, 파라미터2 = 속성값 )
		model.addAttribute( "greeting", "안녕하세요" + name );
		
		// hello.jsp 로 연결된다.
		// webapp/WEB-INF/view 와 .jsp 는 어디로 날아갔느냐? MvcConfig.java 에서 설정했다.
		return "hello";
	}
}
