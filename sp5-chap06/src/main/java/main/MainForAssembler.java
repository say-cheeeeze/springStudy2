package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import assembler.Assembler;
import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberNotFoundException;
import spring.MemberRegistService;
import spring.RegisterRequest;
import spring.WrongIdPasswordException;

/**
 * Assembler 를 사용하는 main method
 * @author cheeeeze
 * @modified 2022/06/03
 *
 */
public class MainForAssembler {
	
	public static Assembler assembler = new Assembler();

	public static void main( String[] args ) throws IOException {
		
		BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
		
		while( true ) {
			
			System.out.println( "명령어를 입력하세요." );
			String command = reader.readLine();
			
			// 대소문자 구별없이 문자열을 비교 
			if ( command.equalsIgnoreCase( "exit" ) ) {
				System.out.println( "종료합니다." );
				break;
			}
			
			if ( command.startsWith( "new " ) ) {
				processNewCommand( command.split( " " ) );
				continue;
			}
			else if ( command.startsWith( "change " ) ) {
				processChangeCommand( command.split( " " ) );
				continue;
			}
			printHelp();
		}
		
	}
	
	private static void processNewCommand( String[] commands ) {
		
		if ( commands.length != 5 ) {
			printHelp();
			return;
		}
		
		MemberRegistService registService = assembler.getMemberRegistService();
		RegisterRequest req = new RegisterRequest();
		req.setEmail( commands[1] );
		req.setName( commands[2] );
		req.setPassword( commands[3] );
		req.setConfirmPassword( commands[4] );
		
		if ( !req.isPasswordEqualToConfirmPassword() ) {
			System.out.println( "입력한 암호가 일치하지 않습니다." );
			return;
		}
		
		try {
			registService.regist( req );
			System.out.println( "회원 등록 완료" );
		}
		catch( DuplicateMemberException e ) {
			System.out.println( "이미 존재하는 회원입니다.\n" );
		}
		
		
	}
	
	private static void processChangeCommand( String[] commands ) {
		
		if ( commands.length != 4 ) {
			printHelp();			
			return;
		}
		
		ChangePasswordService changePwdService = assembler.getChangePasswordService();
		
		try {
			changePwdService.changePassword( commands[1], commands[2], commands[3] );
			System.out.println( "암호를 변경했습니다.\n" );
		}
		catch( MemberNotFoundException e ) {
			System.out.println( "존재하지 않는 이메일입니다." );
		}
		catch( WrongIdPasswordException e ) {
			System.out.println( "이메일과 암호가 일치하지 않습니다." );
		}
		
	}
	
	public static void printHelp() {
		
		System.out.println();
		System.out.println( "잘못된 명령입니다." );
		System.out.println( "new 이메일 이름 암호 암호확인" );
		System.out.println( "change 이메일 현재암호 변경할암호" );
		System.out.println();
	}
	
	
	
}
