package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	
	public static void main( String[] args ) {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext( AppContext.class );
		
		Greeter greeter = ctx.getBean( "greeter", Greeter.class );
		Greeter greeter2 = ctx.getBean( "greeter", Greeter.class );
		String message = greeter.greet( "cheeeeze님" );
		System.out.println( message );
		
		isSingleTonObject( greeter, greeter2 );
		
		ctx.close();
	}
	
	private static void isSingleTonObject( Object object, Object object2 ) {
		System.out.println( "###### isSingleTonObject ######" );
		System.out.println( "greeter 와 greeter2 는 같은 객체라고 볼 수 있는가?" );
		System.out.println( object == object2 );
		System.out.println( "###### isSingleTonObject ######" );
	}
}
