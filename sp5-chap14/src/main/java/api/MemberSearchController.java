package api;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.Member;
import spring.MemberDAO;

@Controller
public class MemberSearchController {
	
	private MemberDAO memberDAO;
	
	public void setMemberDAO( MemberDAO memberDAO ) {
		this.memberDAO = memberDAO;
	}
	
	@RequestMapping( "/members" )
	public String memberList( @ModelAttribute( "cmd" ) SearchCommand searchCommand, Errors errors, Model model ) {
		
		if ( errors.hasErrors() ) {
			return "member/memberList";
		}
		
		
		if ( searchCommand.getFrom() != null && searchCommand.getTo() != null ) {
			
			List<Member> memberList = memberDAO.selectByRegdate( searchCommand.getFrom(), searchCommand.getTo() );
			
			model.addAttribute( "memberList", memberList );
			
		}
		
		return "member/memberList";
		
	}

}
