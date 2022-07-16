package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConfig;
import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberNotFoundException;
import spring.MemberRegistService;
import spring.RegisterRequest;
import spring.WrongIdPasswordException;

public class Main {
	
	private static AnnotationConfigApplicationContext ctx = null;
	
	public static void main( String[] args ) throws IOException {
		
		ctx = new AnnotationConfigApplicationContext( AppConfig.class );
		
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
			else if ( command.startsWith( "list" ) ) {
				processListAllCommand();
				continue;
			}
			else if ( command.startsWith( "info " ) ) {
				processInfoCommand( command.split(" "));
				continue;
			}
			printHelp();
			
		}
		
		
	}

	/**
	 * 신규 회원 등록
	 */
	private static void processNewCommand( String[] commands ) {
		
		if ( commands.length != 5 ) {
			printHelp();
			return;
		}
		
		MemberRegistService registService = ctx.getBean( "memberRegistService", MemberRegistService.class );
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
			long memberId = registService.regist( req );
			System.out.println( "======== 회원 등록 완료 ( " + memberId + " ) ======" );
		}
		catch( DuplicateMemberException e ) {
			System.out.println( "이미 존재하는 회원입니다.\n" );
		}
		
		
	}
	
	/**
	 * 패스워드 변경
	 */
	private static void processChangeCommand( String[] commands ) {
		
		if ( commands.length != 4 ) {
			printHelp();			
			return;
		}
		
		ChangePasswordService changePwdService = ctx.getBean( "changePasswordService", ChangePasswordService.class );
		
		try {
			changePwdService.changePassword( commands[1], commands[2], commands[3] );
			System.out.println( "======== 암호를 변경했습니다. ======== \n" );
		}
		catch( MemberNotFoundException e ) {
			System.out.println( "존재하지 않는 이메일입니다." );
		}
		catch( WrongIdPasswordException e ) {	
			System.out.println( "이메일과 암호가 일치하지 않습니다." );
		} 
		
	}
	
	public static void processInfoCommand( String[] args ) {
		
		if ( args.length != 2 ) {
			printHelp();
			return;
		}
		
		MemberInfoPrinter printer = ctx.getBean( "memberInfoPrinter", MemberInfoPrinter.class );
		printer.printMemberInfo( args[1] );
	}
	
	/**
	 * 모든 회원조회
	 */
	private static void processListAllCommand() {
		
		MemberListPrinter listPrinter = ctx.getBean( "memberListPrinter", MemberListPrinter.class );
		listPrinter.printAll();
		
	}
	
	public static void printHelp() {
		
		System.out.println( "================" );
		System.out.println( "신규 회원 추가 : new 이메일 이름 암호 암호확인" );
		System.out.println( "비밀번호 변경 : change 이메일 현재암호 변경할암호" );
		System.out.println( "회원 단건 정보 조회 : info 이메일" );
		System.out.println( "모든 회원 목록 조회 : list" );
		System.out.println( "프로그램 종료 : exit" );
		System.out.println( "================" );
	}

}
