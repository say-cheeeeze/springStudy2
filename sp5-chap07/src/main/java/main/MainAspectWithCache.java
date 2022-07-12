package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap07.Calculator;
import config.AppConfigWithCache;

public class MainAspectWithCache {
	
	public static void main( String[] args ) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( AppConfigWithCache.class );
		
		Calculator cal = context.getBean( "calculator", Calculator.class );
		
		cal.factorial( 7 );
		cal.factorial( 7 );
		cal.factorial( 5 );
		cal.factorial( 5 );
		context.close();
	}

}
