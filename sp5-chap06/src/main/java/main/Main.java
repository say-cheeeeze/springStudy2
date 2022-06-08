package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import config.AppCtxForMain;
import spring.Client;

public class Main {

	public static void main( String[] args ) {
		
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext( AppCtxForMain.class );
		
		Client client = ctx.getBean( Client.class );
		
		client.send();
		
		ctx.close();
	}
}
