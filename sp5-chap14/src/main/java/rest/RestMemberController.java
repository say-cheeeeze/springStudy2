package rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring.DuplicateMemberException;
import spring.Member;
import spring.MemberDAO;
import spring.MemberNotFoundException;
import spring.MemberRegistService;
import spring.RegisterRequest;

@RestController
public class RestMemberController {

	private MemberDAO memberDAO;
	
	private MemberRegistService memberRegistService;
	
	@GetMapping( "/api/member/listAll" )
	public List<Member> members() {
		return memberDAO.listAll();
	}
	
	@GetMapping( "/api/member/{id}" )
	public Member getMember( @PathVariable Long id ) {
		
		Member member = memberDAO.selectById( id );
		
		if ( member == null ) {
			throw new MemberNotFoundException(); 
		}
		return member;
	}
	
	@PostMapping( "/api/member/insert" )
	public void insertMember( @RequestBody @Valid RegisterRequest req, HttpServletResponse response ) throws IOException {
		
		try {
			Long memberId = memberRegistService.regist( req );
			response.setHeader( "Location", "/api/member/insert/" + memberId );
			response.setStatus( HttpServletResponse.SC_CREATED );
		}
		catch( DuplicateMemberException e ) {
			e.printStackTrace();
			response.sendError( HttpServletResponse.SC_CONFLICT );
		}
		
	}
	
	@ExceptionHandler( MemberNotFoundException.class )
	public ResponseEntity<ErrorResponse> handleNoMember() {
		return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( new ErrorResponse( "no member" ) );
	}
	
	
	public void setMemberDAO( MemberDAO memberDAO ) {
		this.memberDAO = memberDAO;
	}
	
	public void setMemberRegistService( MemberRegistService memberRegistService ) {
		this.memberRegistService = memberRegistService;
	}
}
