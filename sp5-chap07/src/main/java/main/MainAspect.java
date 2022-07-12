package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap07.Calculator;
import chap07.RecCalculator;
import config.AppConfig;

public class MainAspect {
	
	public static void main( String[] args ) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
		
		// 스프링이 프록시 객체를 생성할 때 실제 생성할 Bean 객체가 인터페이스를 상속한 객체라면
		// 인터페이스를 이용해서 프록시를 생성하는데, 실제 bean 의 타입이 RecCalculator 클래스라고 하더라도
		// calculator 이름에 해당하는 bean 객체의 타입은 Calculator 인터페이스를 상속받은 프록시 타입이 된다.
		// 따라서 아래 코드는 RecCalculator 로 타입 변환을 할 수 없기 때문에 익셉션이 발생한다.
		// RecCalculator cal = context.getBean( "calculator", RecCalculator.class );
		
		
		Calculator cal = context.getBean( "calculator", Calculator.class );
		
		long fiveFactorial = cal.factorial( 5 );
		
		System.out.println( "cal.factorial( 5 ) = " + fiveFactorial );
		
		// 여기서 cal.getClass() 하면 
		// RecCalculator 가 찍히는게 아니라 com.sun.proxy.$Proxy17 객체가 찍히는 것을 확인할 수 있다.
		// 왜냐면 설정클래스 AppConfig 에 ExeTimeAspect 를 Bean 으로 설정했기 때문에 Aspect 를 타기 때문
		System.out.println( cal.getClass().getName() );
		
		context.close();
		
		
	}

}
