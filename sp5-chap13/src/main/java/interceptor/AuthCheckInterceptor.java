package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * org.springframework.web.servlet.HandlerInterceptor 인터페이스를 상속받은 객체
 * preHandle(), postHandle(), afterCompletion() 중 필요한 메서드를 재정의. 
 * @author cheeeeze
 *
 */
public class AuthCheckInterceptor implements HandlerInterceptor {
	
	/**
	 * preHandle() 메서드를 통해 비밀번호 변경 화면에 접근할 때 로그인여부에 따라
	 * 정상적으로 로그인 화면으로 보내거나 컨트롤러를 실행하도록 구현할 수 있다.
	 * 만약 session 에 어떤 값이 없으면 지정한 경로로 리다이렉트 시키는 것도 가능.
	 * 
	 * 이 preHandle() 메서드가 true 를 반환하면 컨트롤러를 실행하게 된다.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession( false );
		
		// HttpSession 에 authInfo 속성이 있으면 true 를 반환한다.
		if ( session != null ) {
			Object authInfo = session.getAttribute( "authInfo" );
			
			if ( authInfo != null ) {
				return true;
			}
		}
		
		// 만약 authInfo 속성이 없으면 로그인화면으로 리다이렉트 시킨다. 
		// http://localhost:8080/sp5-chap13 에서 컨텍스트 경로는 /sp5-chap13 이 된다.
		// 따라서 /sp5-chap13/login 으로 리다이렉트하도록 응답을 전송한다.
		response.sendRedirect( request.getContextPath() + "/login" );
		return false;
	}

}
