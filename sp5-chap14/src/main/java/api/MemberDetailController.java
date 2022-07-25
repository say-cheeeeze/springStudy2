package api;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import spring.Member;
import spring.MemberDAO;
import spring.MemberNotFoundException;

@Controller
public class MemberDetailController {
	
	private MemberDAO memberDAO;
	
	public void setMemberDAO( MemberDAO memberDAO ) {
		this.memberDAO = memberDAO;
	}
	
	@GetMapping( "/members/{id}" )
	public String memberDetail( @PathVariable( "id" ) Long memberId, Model model ) {
		
		Member member = memberDAO.selectById( memberId );
		
		if ( member == null ) {
			throw new MemberNotFoundException();
		}
		
		model.addAttribute( "member", member );
		
		return "member/memberDetail";
	}
	
	@ExceptionHandler( TypeMismatchException.class )
	public String handleTypeMismatchException() {
		return "member/invalidId";
	}
	
	@ExceptionHandler( MemberNotFoundException.class )
	public String memberNotFoundException() {
		return "member/notFoundMember";
	}

}
